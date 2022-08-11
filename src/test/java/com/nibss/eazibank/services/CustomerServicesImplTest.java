package com.nibss.eazibank.services;

import com.nibss.eazibank.data.models.Account;
import com.nibss.eazibank.data.models.Customer;
import com.nibss.eazibank.data.repositories.AccountRepository;
import com.nibss.eazibank.data.repositories.CustomerRepository;
import com.nibss.eazibank.dto.request.CreateCustomerRequest;
import com.nibss.eazibank.dto.request.CustomerDepositRequest;
import com.nibss.eazibank.dto.request.CustomerTransferRequest;
import com.nibss.eazibank.dto.request.CustomerWithdrawalRequest;
import com.nibss.eazibank.dto.response.CreateCustomerResponse;
import com.nibss.eazibank.dto.response.CustomerDepositResponse;
import com.nibss.eazibank.dto.response.CustomerTransferResponse;
import com.nibss.eazibank.dto.response.CustomerWithdrawalResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServicesImplTest {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerServices customerServices;

    @Test
    public void customerCanBeCreatedTest(){
        assertEquals(0, accountRepository.count());
        CreateCustomerRequest createCustomerRequest = new  CreateCustomerRequest("Ayobaye", "Ogundele",
                                            "07048847840", "", "", "2000-01-20 00:00");
        CreateCustomerResponse createdCustomer = customerServices.createCustomer(createCustomerRequest);
        assertEquals(1, accountRepository.count());
        assertEquals(1, customerRepository.count());

        Optional<Account> accountRepositoryAccount = accountRepository.findByFirstName("Ayobaye");
        Account accountRepoAccount = accountRepositoryAccount.get();
        Optional<Customer> customerRepositoryCustomer = customerRepository.findByFirstName("Ayobaye");
        Account customerRepoAccount = customerRepositoryCustomer.get().getCustomerAccounts().get(createdCustomer.getAccountNumber());

        // test that account saved in account repo is same customer has
        assertEquals(accountRepoAccount.getAccountNumber(), customerRepoAccount.getAccountNumber());
        // test that account saved in account repo is same customer services createCustomer method saved
        assertEquals(accountRepoAccount.getAccountNumber(), createdCustomer.getAccountNumber());
        //checking is what enters is what comes of
        assertEquals(createdCustomer.getAccountNumber(), customerRepoAccount.getAccountNumber());
    }

    @Test
    public void customerCanDepositTest(){
        CreateCustomerRequest createCustomerRequest = new  CreateCustomerRequest("Ayobaye", "Ogundele",
                "07048847840", "", "", "2000-01-20 00:00");
        CreateCustomerResponse createdCustomer = customerServices.createCustomer(createCustomerRequest);
        assertEquals(1, accountRepository.count());
        assertEquals(1, customerRepository.count());

        CustomerDepositRequest depositRequest = new CustomerDepositRequest(createdCustomer.getAccountNumber(), BigInteger.valueOf(100000000));
        CustomerDepositResponse depositResponse = customerServices.deposit(depositRequest);
        assertTrue(depositResponse.isSuccessful());
        assertEquals(new BigInteger("100000000"), depositResponse.getAmount());
    }

    @Test
    public void customerCanWithdrawTest() {
        CreateCustomerRequest createCustomerRequest = new  CreateCustomerRequest("Ayobaye", "Ogundele",
                "07048847840", "", "", "2000-01-20 00:00");
        CreateCustomerResponse createdCustomer = customerServices.createCustomer(createCustomerRequest);
        assertEquals(1, accountRepository.count());
        assertEquals(1, customerRepository.count());

        CustomerDepositRequest depositRequest = new CustomerDepositRequest(createdCustomer.getAccountNumber(), BigInteger.valueOf(1000));
        CustomerDepositResponse depositResponse = customerServices.deposit(depositRequest);

        CustomerWithdrawalRequest withdrawalRequest = new CustomerWithdrawalRequest(createdCustomer.getAccountNumber(), BigInteger.valueOf(150));
        CustomerWithdrawalResponse withdrawalResponse = customerServices.withdraw(withdrawalRequest);
        assertTrue(withdrawalResponse.isSuccessful());
        assertEquals(new BigInteger("150"), withdrawalResponse.getAmount());

        Customer customer = customerRepository.findByFirstName("Ayobaye").get();
        Account customerAccount = customer.getCustomerAccounts().get(createdCustomer.getAccountNumber());
        assertEquals(new BigInteger("850"), customerAccount.getBalance());
    }

    @Test
    public void customerCanTransferFromOneCustomerToAnotherCustomerTest() {
        CreateCustomerRequest createCustomerRequest1 = new  CreateCustomerRequest("Ayobaye", "Ogundele",
                "07048847840", "", "", "2000-01-20 00:00");
        CreateCustomerResponse createdCustomer1 = customerServices.createCustomer(createCustomerRequest1);

        CreateCustomerRequest createCustomerRequest2 = new  CreateCustomerRequest("Olayemi", "Gabriel",
                "07041941940", "", "", "2000-01-20 00:00");
        CreateCustomerResponse createdCustomer2 = customerServices.createCustomer(createCustomerRequest2);
        assertEquals(2, accountRepository.count());
        assertEquals(2, customerRepository.count());

        CustomerDepositRequest depositRequest = new CustomerDepositRequest(createdCustomer1.getAccountNumber(), BigInteger.valueOf(10000));
        customerServices.deposit(depositRequest);
        CustomerTransferRequest transferRequest = new CustomerTransferRequest(createdCustomer2.getAccountNumber(), createdCustomer1.getAccountNumber(), BigInteger.valueOf(3000));
        CustomerTransferResponse transferResponse = customerServices.transfer(transferRequest);

        Customer sender = customerRepository.findByFirstName("Ayobaye").get();
        Account customer1Account = sender.getCustomerAccounts().get(createdCustomer1.getAccountNumber());
        Customer receiver = customerRepository.findByFirstName("Olayemi").get();
        Account customer2Account = receiver.getCustomerAccounts().get(createdCustomer2.getAccountNumber());

        assertTrue(transferResponse.isSuccessful());
        assertEquals(new BigInteger("3000"), transferResponse.getAmount());
        assertEquals(new BigInteger("7000"), customer1Account.getBalance());
        assertEquals(new BigInteger("3000"), customer2Account.getBalance());
    }
}