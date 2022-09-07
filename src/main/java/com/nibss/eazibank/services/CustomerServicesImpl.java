package com.nibss.eazibank.services;

import com.nibss.eazibank.data.models.Account;
import com.nibss.eazibank.data.models.Customer;
import com.nibss.eazibank.data.models.Transaction;
import com.nibss.eazibank.data.models.enums.TransactionType;
import com.nibss.eazibank.data.repositories.CustomerRepository;
import com.nibss.eazibank.data.repositories.TransactionRepository;
import com.nibss.eazibank.dto.request.*;
import com.nibss.eazibank.dto.response.*;
import com.nibss.eazibank.exception.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional
public class CustomerServicesImpl implements CustomerServices{

    @Autowired
    private AccountServices accountServices = new AccountServicesImpl();
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest) {
        validateCustomerExistence(createCustomerRequest);
        Customer customer = new Customer();
        customer.setFirstName(createCustomerRequest.getFirstName());
        customer.setLastName(createCustomerRequest.getLastName());
        customer.setEmail(createCustomerRequest.getEmail());
        customer.setMothersMaidenName(createCustomerRequest.getMothersMaidenName());
        customer.setPhoneNumber(createCustomerRequest.getPhoneNumber());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        customer.setDOB(LocalDateTime.parse(createCustomerRequest.getDOB(), formatter));

        RegisterAccountRequest request = new RegisterAccountRequest();
        request.setFirstName(customer.getFirstName());
        request.setLastName(customer.getLastName());
        request.setPhoneNumber(customer.getPhoneNumber());
        request.setAccountType(createCustomerRequest.getAccountType());

        RegisterAccountResponse createdAccount = accountServices.createAccount(request);
        customer.setBVN(createdAccount.getBankVerificationNumber());

        Optional<Account> optionalAccount = accountServices.findAccount(createdAccount.getAccountNumber());
        if(optionalAccount.isEmpty()) throw new EaziBankExceptions("Error finding account after saving");
        Account account = optionalAccount.get();
//        DateTimeFormatter format = DateTimeFormatter.ofPattern("EEEE, dd/MM/yyy, hh:mm, a");
//        account.setAccountCreationDate(LocalDateTime.parse(createdAccount.getDateCreated(), format));
        Map<String, Account> customerAccounts = new HashMap<>();
        customerAccounts.put(account.getAccountNumber(), account);
        customer.setCustomerAccounts(customerAccounts);
        Customer createdCustomer = customerRepository.save(customer);

        CreateCustomerResponse response = new CreateCustomerResponse();
        response.setFirstName(createdCustomer.getFirstName());
        response.setLastName(createdCustomer.getLastName());
        response.setAccountNumber(createdCustomer.getCustomerAccounts().get(account.getAccountNumber()).getAccountNumber());
        response.setBVN(createdCustomer.getBVN());
        return response;
    }

    private void validateCustomerExistence(CreateCustomerRequest createCustomerRequest) {
        Optional<Customer> optionalCustomer = customerRepository.findByMothersMaidenName(createCustomerRequest.getMothersMaidenName());
        if(optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            Map<String, Account> accounts = customer.getCustomerAccounts();
            List<Account> accountList = new ArrayList<>(accounts.values());
            for(Account account : accountList) {
                if(account.getAccountType().toString().equals(createCustomerRequest.getAccountType())) {
                    throw new CustomerAlreadyExistException("Customer already exist!");
                }
            }
        }
    }

    @Override
    public CustomerDepositResponse deposit(CustomerDepositRequest depositRequest) {
        Optional<Account> repoAccount = accountServices.findAccount(depositRequest.getAccountNumber());
        if (repoAccount.isEmpty()) throw new AccountDoesNotExistException("Account does not exist");
        CreditAccountResponse creditResponse = accountServices.creditAccount(new CreditAccountRequest(depositRequest.getAccountNumber(), depositRequest.getAmount()));
        Optional<Account> optionalCreditedAccount = accountServices.findAccount(creditResponse.getAccountNumber());
        Account creditedAccount = optionalCreditedAccount.get();
        Transaction transaction = new Transaction();
        transaction.setAmount(depositRequest.getAmount());
        transaction.setDescription(depositRequest.getNarration());
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setBeneficiaryAccountNumber(depositRequest.getAccountNumber());
        transaction.setBeneficiaryName("Self");
        transaction.setTransactionId("001");
//        transactionRepository.save(transaction);



        Optional<Customer> customerInRepository = customerRepository.findCustomerByBVN(creditedAccount.getBankVerificationNumber());
        Customer customer = customerInRepository.get();
        Map<String, Account> customersAccounts = customer.getCustomerAccounts();
        Account accountToBeUpdated = customersAccounts.get(creditedAccount.getAccountNumber());
        customer.getCustomerAccounts().replace(accountToBeUpdated.getAccountNumber(), creditedAccount);

        List<Transaction> transactionHistory = customer.getTransactionHistory();
        transactionHistory.add(transaction);

        customer.setTransactionHistory(transactionHistory);
        Customer creditedCustomer = customerRepository.save(customer);

        CustomerDepositResponse depositResponse = modelMapper.map(creditedCustomer, CustomerDepositResponse.class);
        depositResponse.setAmount(depositRequest.getAmount());
        depositResponse.setSuccessful(true);
        return depositResponse;
    }

    @Override
    public CustomerWithdrawalResponse withdraw(CustomerWithdrawalRequest withdrawalRequest) {
        Optional<Account> repoAccount = accountServices.findAccount(withdrawalRequest.getAccountNumber());
        if (repoAccount.isEmpty()) throw new AccountDoesNotExistException("Account does not exist");
        Account tempAccount = repoAccount.get();
        BigInteger currentBalance = tempAccount.getBalance();
        if (withdrawalRequest.getAmount().compareTo(currentBalance) >= 1) throw new InsufficientBalanceException("Inadequate balance");
        DebitAccountResponse debitResponse = accountServices.debitAccount(new DebitAccountRequest(
                withdrawalRequest.getAccountNumber(), withdrawalRequest.getAmount()));

        Optional<Customer> optionalCustomer = customerRepository.findCustomerByBVN(repoAccount.get().getBankVerificationNumber());
        Customer customer = optionalCustomer.get();
        Map<String, Account> customersAccounts = customer.getCustomerAccounts();
        Account account = accountServices.findAccount(withdrawalRequest.getAccountNumber()).get();
        customersAccounts.replace(withdrawalRequest.getAccountNumber(), account);
        Customer debitedCustomer = customerRepository.save(customer);

        CustomerWithdrawalResponse withdrawalResponse = modelMapper.map(debitedCustomer, CustomerWithdrawalResponse.class);
        withdrawalResponse.setAmount(withdrawalRequest.getAmount());
        withdrawalResponse.setSuccessful(true);
        return withdrawalResponse;
    }

    @Override
    public CustomerTransferResponse transfer(CustomerTransferRequest transferRequest) {
        Optional<Account> optionalSendersAccount = accountServices.findAccount(transferRequest.getSendersAccountNumber());
        if (optionalSendersAccount.isEmpty()) throw new AccountDoesNotExistException("Account does not exist. Check you account number");
        Optional<Account> optionalReceiversAccount;
        try {
            optionalReceiversAccount = accountServices.findAccount(transferRequest.getReceiversAccountNumber());
        }
        catch (AccountDoesNotExistException e) {
            throw new InvalidRecipientException("Invalid recipient number");
        }
        optionalReceiversAccount.get();
        Account sendersAccount = optionalSendersAccount.get();

        if(sendersAccount.getBalance().subtract(transferRequest.getAmount()).compareTo(BigInteger.valueOf(23)) <= 0) throw new InsufficientBalanceException("Insufficient balance");
        CustomerWithdrawalResponse sendersAccountWithdrawalResponse = withdraw(new CustomerWithdrawalRequest(transferRequest.getSendersAccountNumber(), transferRequest.getAmount()));
        if (!sendersAccountWithdrawalResponse.isSuccessful()) throw new EaziBankExceptions("An error occured! Please try again");
        CustomerDepositResponse receiversAccountDepositResponse = deposit(new CustomerDepositRequest(transferRequest.getReceiversAccountNumber(), transferRequest.getAmount()));
        if (!receiversAccountDepositResponse.isSuccessful()) throw new EaziBankExceptions("An error occured! Please try again");

        CustomerTransferResponse transferResponse = new CustomerTransferResponse();
        transferResponse.setFirstName(receiversAccountDepositResponse.getFirstName());
        transferResponse.setLastName(receiversAccountDepositResponse.getLastName());
        transferResponse.setAmount(receiversAccountDepositResponse.getAmount());
        transferResponse.setSuccessful(true);
        transferResponse.setAccountNumber(receiversAccountDepositResponse.getAccountNumber());
        transferResponse.setMessage(String.format("Transfer of %d to %s is successful", transferResponse.getAmount(), transferResponse.getAccountNumber()));
        return transferResponse;
    }

    @Override
    public CreatePasswordResponse setCustomerPassword(CreatePasswordRequest createPasswordRequest) {
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByEmail(createPasswordRequest.getEmail());
        Customer customer = optionalCustomer.get();
        customer.setPassword(createPasswordRequest.getPassword());
        customer.setConfirmPassword(createPasswordRequest.getConfirmPassword());
        Customer savedCustomer = customerRepository.save(customer);

        return CreatePasswordResponse.builder()
                .firstName(savedCustomer.getFirstName())
                .lastName(savedCustomer.getLastName())
                .success(true)
                .build();
    }

    @Override
    public CustomerLoginResponse login(CustomerLoginRequest customerLoginRequest) {
        Optional<Customer> optionalCustomer = Optional.empty();
        if(customerLoginRequest.getPhoneNumber().equals("")) {
            optionalCustomer = customerRepository.findCustomerByEmail(customerLoginRequest.getEmail());
        }else if(customerLoginRequest.getEmail().equals("")){
            optionalCustomer = customerRepository.findCustomerByPhoneNumber(customerLoginRequest.getPhoneNumber());
        }
        if (optionalCustomer.isEmpty()) throw new InvalidLoginDetailException("Invalid login details");
        Customer customer = optionalCustomer.get();
        if (!customer.getPassword().equals(customerLoginRequest.getPassword()))
            throw new InvalidLoginDetailException("Invalid login details");
        return CustomerLoginResponse.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .BVN(customer.getBVN())
                .customerAccounts(customer.getCustomerAccounts())
                .success(true)
                .build();
    }


    @Override
    public ViewProfileResponse viewCustomerProfile(ViewProfileRequest viewProfileRequest) {
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByEmail(viewProfileRequest.getEmail());
        if(optionalCustomer.isEmpty()) throw new InvalidLoginDetailException("Customer does not exist exception");
        Customer customer = optionalCustomer.get();
        return ViewProfileResponse.builder()
                .BVN(customer.getBVN())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phoneNumber(customer.getPhoneNumber())
                .email(customer.getEmail())
                .DOB(customer.getDOB())
                .build();
        }

    @Override
    public ViewTransactionHistoryResponse viewTransactionHistory(ViewTransactionHistoryRequest viewTransactionHistoryRequest) {
        customerRepository.findCustomerByEmail(viewTransactionHistoryRequest.getEmail());
        return null;
    }
}
