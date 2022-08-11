package com.nibss.eazibank.services;

import com.nibss.eazibank.data.models.Account;
import com.nibss.eazibank.data.models.Customer;
import com.nibss.eazibank.data.repositories.CustomerRepository;
import com.nibss.eazibank.dto.request.*;
import com.nibss.eazibank.dto.response.*;
import com.nibss.eazibank.exception.EaziBankExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerServicesImpl implements CustomerServices{

    @Autowired
    private AccountServices accountServices = new AccountServicesImpl();
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest) {
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
        return response;
    }

    @Override
    public CustomerDepositResponse deposit(CustomerDepositRequest depositRequest) {
        Optional<Account> repoAccount = accountServices.findAccount(depositRequest.getAccountNumber());
        CreditAccountResponse creditResponse = accountServices.creditAccount(new CreditAccountRequest(depositRequest.getAccountNumber(), depositRequest.getAmount()));
        Optional<Account> optionalCreditedAccount = accountServices.findAccount(creditResponse.getAccountNumber());
        Account creditedAccount = optionalCreditedAccount.get();

        Optional<Customer> customerInRepository = customerRepository.findCustomerByBVN(creditedAccount.getBVN());
        Customer customer = customerInRepository.get();
        Map<String, Account> customersAccounts = customer.getCustomerAccounts();
        Account tobeUpdatedAccount = customersAccounts.get(creditedAccount.getAccountNumber());
        customer.getCustomerAccounts().replace(tobeUpdatedAccount.getAccountNumber(), creditedAccount);
        Customer creditedCustomer = customerRepository.save(customer);

//        Optional<Customer> customerRepo = customerRepository.findByCustomerAccounts();

        CustomerDepositResponse depositResponse = new CustomerDepositResponse();
        depositResponse.setFirstName(creditedCustomer.getFirstName());
        depositResponse.setLastName(creditedCustomer.getLastName());
        depositResponse.setAmount(depositRequest.getAmount());
        depositResponse.setSuccessful(true);
        return depositResponse;
    }

    @Override
    public CustomerWithdrawalResponse withdraw(CustomerWithdrawalRequest withdrawalRequest) {
        Optional<Account> repoAccount = accountServices.findAccount(withdrawalRequest.getAccountNumber());
        DebitAccountResponse debitResponse = accountServices.debitAccount(new DebitAccountRequest(
                withdrawalRequest.getAccountNumber(), withdrawalRequest.getAmount()));

        Optional<Customer> optionalCustomer = customerRepository.findCustomerByBVN(repoAccount.get().getBVN());
        Customer customer = optionalCustomer.get();
        Map<String, Account> customersAccounts = customer.getCustomerAccounts();
        Account account = accountServices.findAccount(withdrawalRequest.getAccountNumber()).get();
        customersAccounts.replace(withdrawalRequest.getAccountNumber(), account);
        Customer debitedCustomer = customerRepository.save(customer);

        CustomerWithdrawalResponse withdrawalResponse = new CustomerWithdrawalResponse();
        withdrawalResponse.setFirstName(debitedCustomer.getFirstName());
        withdrawalResponse.setLastName(debitedCustomer.getLastName());
        withdrawalResponse.setAmount(withdrawalRequest.getAmount());
        withdrawalResponse.setSuccessful(true);
        return withdrawalResponse;
    }

    @Override
    public CustomerTransferResponse transfer(CustomerTransferRequest transferRequest) {
        Optional<Account> optionalSendersAccount = accountServices.findAccount(transferRequest.getSendersAccountNumber());
        if (optionalSendersAccount.isEmpty()) throw new EaziBankExceptions("Account does not exist. Check you account number");
        Optional<Account> optionalReceiversAccount = accountServices.findAccount(transferRequest.getReceiversAccountNumber());
        if (optionalReceiversAccount.isEmpty()) throw new EaziBankExceptions("Wrong account number. Check the account number entered");
        Account receiversAccount = optionalReceiversAccount.get();
        Account sendersAccount = optionalSendersAccount.get();

        CustomerWithdrawalResponse withdrawalResponse = withdraw(new CustomerWithdrawalRequest(transferRequest.getSendersAccountNumber(), transferRequest.getAmount()));
        if (!withdrawalResponse.isSuccessful()) throw new EaziBankExceptions("An error occured! Please try again");
        CustomerDepositResponse depositResponse = deposit(new CustomerDepositRequest(transferRequest.getReceiversAccountNumber(), transferRequest.getAmount()));
        if (!depositResponse.isSuccessful()) throw new EaziBankExceptions("An error occured! Please try again");

        CustomerTransferResponse transferResponse = new CustomerTransferResponse();
        transferResponse.setFirstName(depositResponse.getFirstName());
        transferResponse.setLastName(depositResponse.getLastName());
        transferResponse.setAmount(depositResponse.getAmount());
        transferResponse.setSuccessful(true);
        transferResponse.setAccountNumber(depositResponse.getAccountNumber());

        transferResponse.setMessage(String.format("Transfer of %d to %s is successful", transferResponse.getAmount(), transferResponse.getAccountNumber()));
        return transferResponse;
    }
}
