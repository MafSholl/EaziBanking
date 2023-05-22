package com.eazibank.remabank.customer.services;

import com.eazibank.remabank.account.dto.request.CreditAccountRequest;
import com.eazibank.remabank.account.dto.request.DebitAccountRequest;
import com.eazibank.remabank.account.dto.request.RegisterAccountRequest;
import com.eazibank.remabank.account.dto.response.CreditAccountResponse;
import com.eazibank.remabank.account.dto.response.DebitAccountResponse;
import com.eazibank.remabank.account.dto.response.RegisterAccountResponse;
import com.eazibank.remabank.account.models.Account;
import com.eazibank.remabank.account.services.AccountServices;
import com.eazibank.remabank.customer.dto.request.*;
import com.eazibank.remabank.customer.dto.response.*;
import com.eazibank.remabank.customer.models.Customer;
import com.eazibank.remabank.customer.repository.CustomerRepository;
import com.eazibank.remabank.exception.exceptions.*;
import com.eazibank.remabank.staff.controller.requests.DepositRequest;
import com.eazibank.remabank.transaction.dto.response.ViewTransactionHistoryResponse;
import com.eazibank.remabank.transaction.models.Transaction;
import com.eazibank.remabank.transaction.models.enums.TransactionType;
import com.eazibank.remabank.transaction.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CustomerServicesImpl implements CustomerServices {

    @Autowired
    private AccountServices accountServices;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ModelMapper modelMapper;



    @Override
    public CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest) {
        validateCustomerExistence(createCustomerRequest);
        Customer customer = createCustomerFromRequest(createCustomerRequest);
        RegisterAccountResponse createdAccount = createNewCustomerAccount(createCustomerRequest, customer);
        customer.setBvn(createdAccount.getBvn());

        Optional<Account> optionalAccount = accountServices.findAccount(createdAccount.getAccountNumber());
        if(optionalAccount.isEmpty()) throw new EaziBankException("Error finding account after saving", HttpStatus.NOT_FOUND.value());
        Account account = optionalAccount.get();
//        DateTimeFormatter format = DateTimeFormatter.ofPattern("EEEE, dd/MM/yyy, hh:mm, a");
//        account.setAccountCreationDate(LocalDateTime.parse(createdAccount.getDateCreated(), format));
        Map<String, Account> customerAccounts = new HashMap<>();
        customerAccounts.put(account.getAccountNumber(), account);
        customer.setCustomerAccounts(customerAccounts);
        Customer createdCustomer = customerRepository.save(customer);

        return convertCustomerToResponse(account, createdCustomer);
    }

    private CreateCustomerResponse convertCustomerToResponse(Account account, Customer createdCustomer) {
        CreateCustomerResponse response = modelMapper.map(createdCustomer, CreateCustomerResponse.class);
        Account customerCreatedAccount = createdCustomer.getCustomerAccounts().get(account.getAccountNumber());
        response.setAccountNumber(customerCreatedAccount.getAccountNumber());
        return response;
    }

    private RegisterAccountResponse createNewCustomerAccount(CreateCustomerRequest createCustomerRequest, Customer customer) {
    RegisterAccountRequest request = modelMapper.map(customer, RegisterAccountRequest.class);
        request.setAccountType(createCustomerRequest.getAccountType());
        RegisterAccountResponse response = accountServices.createAccount(request);
        return response;
    }

    private Customer createCustomerFromRequest(CreateCustomerRequest createCustomerRequest) {
        Customer customer = modelMapper.map(createCustomerRequest, Customer.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        customer.setDOB(LocalDateTime.parse(createCustomerRequest.getDOB(), formatter));
        return customer;
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
    public CustomerDepositResponse deposit(DepositRequest depositRequest) {
        checkIfCustomerExist(depositRequest);
        CreditAccountResponse creditResponse = accountServices.creditAccount(new CreditAccountRequest(depositRequest.getAccountNumber(), depositRequest.getAmount()));
        Optional<Account> optionalCreditedAccount = accountServices.findAccount(creditResponse.getAccountNumber());
        Account creditedAccount = optionalCreditedAccount.get();
        Transaction transaction = Transaction.builder()
                .accountNumber(depositRequest.getAccountNumber())
                .amount(depositRequest.getAmount())
                .description(depositRequest.getNarration())
                .transactionType(TransactionType.DEPOSIT)
                .recipientAccountNumber(depositRequest.getAccountNumber())
                .recipientName("Self")
                .transactionId("001")
                .build();
        transactionRepository.save(transaction);

        Optional<Customer> customerInRepository = customerRepository.findCustomerByCustomerAccount(creditedAccount.getAccountNumber());
        Customer customer = customerInRepository.get();
        List<Transaction> transactionHistory = customer.getTransactionHistory();
        transactionHistory.add(transaction);
        customer.setTransactionHistory(transactionHistory);
        Map<String, Account> customersAccounts = customer.getCustomerAccounts();
        Account accountToBeUpdated = customersAccounts.get(creditedAccount.getAccountNumber());
        customer.getCustomerAccounts().replace(accountToBeUpdated.getAccountNumber(), creditedAccount);
        Customer creditedCustomer = customerRepository.save(customer);

        CustomerDepositResponse depositResponse = modelMapper.map(creditedCustomer, CustomerDepositResponse.class);
        depositResponse.setAmount(depositRequest.getAmount());
        depositResponse.setSuccess(true);
        return depositResponse;
    }

    private void checkIfCustomerExist(DepositRequest depositRequest) {
        Optional<Account> repoAccount = accountServices.findAccount(depositRequest.getAccountNumber());
        if (repoAccount.isEmpty()) throw new AccountDoesNotExistException("Account does not exist");
    }

    @Override
    @Transactional
    public CustomerWithdrawalResponse withdraw(CustomerWithdrawalRequest withdrawalRequest) {
        Optional<Account> repoAccount = accountServices.findAccount(withdrawalRequest.getAccountNumber());
        if (repoAccount.isEmpty()) throw new AccountDoesNotExistException("Account does not exist");
        Account tempAccount = repoAccount.get();
        BigInteger currentBalance = tempAccount.getBalance();
        if (withdrawalRequest.getAmount().compareTo(currentBalance) >= 1) throw new InsufficientBalanceException("Inadequate balance");
        DebitAccountResponse debitResponse = accountServices.debitAccount(new DebitAccountRequest(
                withdrawalRequest.getAccountNumber(), withdrawalRequest.getAmount()));
        createAndSaveTransaction(withdrawalRequest);

        Optional<Customer> optionalCustomer = customerRepository.findCustomerByBvn(repoAccount.get().getBvn());
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
    private void createAndSaveTransaction(CustomerWithdrawalRequest withdrawalRequest) {
        //This is wrong! A transaction service layer should create and save transaction
        Transaction transaction = Transaction.builder()
                .accountNumber(withdrawalRequest.getAccountNumber())
                .amount(withdrawalRequest.getAmount())
                .description(withdrawalRequest.getDescription())
                .transactionType(TransactionType.DEPOSIT)
                .recipientAccountNumber(withdrawalRequest.getAccountNumber())
                .recipientName("Self")
                .transactionId("002")
                .build();
        transactionRepository.save(transaction);
    }

    @Override
    public CustomerTransferResponse transfer(CustomerTransferRequest transferRequest) {
        Optional<Account> optionalSendersAccount = accountServices.findAccount(transferRequest.getSendersAccountNumber());
        if (optionalSendersAccount.isEmpty()) throw new AccountDoesNotExistException("Account does not exist. Check you account number");
        Optional<Account> optionalRecipientAccount;
        try {
            optionalRecipientAccount = accountServices.findAccount(transferRequest.getReceiversAccountNumber());
        }
        catch (AccountDoesNotExistException e) {
            throw new InvalidRecipientException("Invalid recipient number");
        }
        optionalRecipientAccount.get();
        Account sendersAccount = optionalSendersAccount.get();

        if(isBalanceEnough(transferRequest, sendersAccount)) throw new InsufficientBalanceException("Insufficient balance");
        CustomerWithdrawalResponse sendersAccountWithdrawalResponse = withdraw(
                new CustomerWithdrawalRequest(transferRequest.getSendersAccountNumber(), transferRequest.getAmount())
        );
        if (!sendersAccountWithdrawalResponse.isSuccessful()) throw new TransactionErrorException("An error occured! Please try again");
        CustomerDepositResponse receiversAccountDepositResponse = deposit(new DepositRequest(transferRequest.getReceiversAccountNumber(), transferRequest.getAmount()));
        if (!receiversAccountDepositResponse.isSuccess()) throw new TransactionErrorException("An error occured! Please try again");

        CustomerTransferResponse transferResponse = modelMapper.map(receiversAccountDepositResponse, CustomerTransferResponse.class);
        transferResponse.setSuccessful(true);
        transferResponse.setMessage(String.format("Transfer of %d to %s is successful", transferResponse.getAmount(), transferResponse.getAccountNumber()));
        return transferResponse;
    }

    private boolean isBalanceEnough(CustomerTransferRequest transferRequest, Account sendersAccount) {
        return sendersAccount.getBalance().subtract(transferRequest.getAmount()).compareTo(BigInteger.valueOf(23)) <= 0;
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
                .BVN(customer.getBvn())
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
                .BVN(customer.getBvn())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phoneNumber(customer.getPhoneNumber())
                .email(customer.getEmail())
                .DOB(customer.getDOB())
                .build();
        }

    @Override
    public ViewTransactionHistoryResponse viewTransactionHistory(ViewTransactionHistoryRequest viewTransactionHistoryRequest) {
        //findByBvn is the appropriate method; it can easily be gotten by the frontend
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByEmail(viewTransactionHistoryRequest.getEmail());
        if (optionalCustomer.isEmpty()) throw new EaziBankException("Customer does not exist", HttpStatus.NOT_FOUND.value());
        //This first method is not advisable as transaction history can grow endlessly. This way we can manage space by saving transactions in the db instead of the models
        Customer customer = optionalCustomer.get();
        List<Transaction> transactionHistory = customer.getTransactionHistory();

        //This second method is the best advisable as cust. doesn't need to be composed of history but history for an account can be gotten from the db instead
        List<Transaction> transactionHistory2 = transactionRepository.findByAccountNumber(viewTransactionHistoryRequest.getAccountNumber());
        ViewTransactionHistoryResponse transactionHistoryResponse = new ViewTransactionHistoryResponse();
        transactionHistoryResponse.setTransactionHistory(transactionHistory2);
        return transactionHistoryResponse;
    }
}
