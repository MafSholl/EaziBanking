package com.nibss.eazibank.services;

import com.nibss.eazibank.data.models.Account;
import com.nibss.eazibank.data.models.Customer;
import com.nibss.eazibank.data.repositories.CustomerRepository;
import com.nibss.eazibank.dto.request.CreateCustomerRequest;
import com.nibss.eazibank.dto.request.CustomerDepositRequest;
import com.nibss.eazibank.dto.request.RegisterAccountRequest;
import com.nibss.eazibank.dto.response.CreateCustomerResponse;
import com.nibss.eazibank.dto.response.CustomerDepositResponse;
import com.nibss.eazibank.dto.response.RegisterAccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServicesImpl implements CustomerServices{

    @Autowired
    private AccountServices accountServices = new AccountServicesImpl();
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDepositResponse deposit(CustomerDepositRequest depositRequest) {

        return null;
    }

    @Override
    public CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest) {
        Customer customer = new Customer();
        customer.setFirstName(createCustomerRequest.getFirstName());
        customer.setLastName(createCustomerRequest.getLastName());
        customer.setEmail(createCustomerRequest.getEmail());
        customer.setMothersMaidenName(createCustomerRequest.getMothersMaidenName());
        customer.setPhoneNumber(createCustomerRequest.getPhoneNumber());
        customer.setBVN("12849939883");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        customer.setDOB(LocalDateTime.parse(createCustomerRequest.getDOB(), formatter));

        RegisterAccountRequest request = new RegisterAccountRequest();
        request.setFirstName(customer.getFirstName());
        request.setLastName(customer.getLastName());
        request.setPhoneNumber(customer.getPhoneNumber());
        RegisterAccountResponse createdAccount = accountServices.createAccount(request);

        Account account = new Account();
        account.setFirstName(createdAccount.getFirstName());
        account.setLastName(createdAccount.getLastName());
        account.setAccountNumber(createdAccount.getAccountNumber());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("EEEE, dd/MM/yyy, hh:mm, a");
        account.setAccountCreationDate(LocalDateTime.parse(createdAccount.getDateCreated(), format));

        List<Account> customerAccounts = new ArrayList<>();
        customerAccounts.add(account);
        customer.setCustomerAccounts(customerAccounts);
        Customer createdCustomer = customerRepository.save(customer);

        CreateCustomerResponse response = new CreateCustomerResponse();
        response.setFirstName(createdCustomer.getFirstName());
        response.setLastName(createdCustomer.getLastName());
        response.setAccountNumber(createdCustomer.getCustomerAccounts().get(customerAccounts.size()-1).getAccountNumber());
        return response;
    }
}
