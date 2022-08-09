package com.nibss.eazibank.services;

import com.nibss.eazibank.data.models.Account;
import com.nibss.eazibank.data.models.Customer;
import com.nibss.eazibank.data.repositories.CustomerRepository;
import com.nibss.eazibank.dto.request.*;
import com.nibss.eazibank.dto.response.*;
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

        Account account = new Account();
        account.setFirstName(createdAccount.getFirstName());
        account.setLastName(createdAccount.getLastName());
        account.setAccountNumber(createdAccount.getAccountNumber());
        account.setBVN(createdAccount.getBankVerificationNumber());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("EEEE, dd/MM/yyy, hh:mm, a");
        account.setAccountCreationDate(LocalDateTime.parse(createdAccount.getDateCreated(), format));

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
        Account account = repoAccount.get();

        Optional<Customer> customerInRepository = customerRepository.findByBVN(account.getBVN());
        Customer customer = customerInRepository.get();
        Account customersAccount = customer.getCustomerAccounts().get(account.getAccountNumber());
        customersAccount.setBalance(creditResponse.getBalance());
        customer.getCustomerAccounts().replace(customersAccount.getAccountNumber(), customersAccount);
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
        Optional<Customer> optionalCustomer = customerRepository.findByBVN(repoAccount.get().getBVN());

        DebitAccountResponse debitResponse = accountServices.debitAccount(new DebitAccountRequest(
                withdrawalRequest.getAccountNumber(), withdrawalRequest.getAmount()));
        Customer customer = optionalCustomer.get();
        Map<String, Account> customersAccounts = customer.getCustomerAccounts();
        System.out.println(accountServices.findAccount(withdrawalRequest.getAccountNumber()).get().getBalance());
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
}
