package com.nibss.eazibank.services;

import com.nibss.eazibank.data.models.Customer;
import com.nibss.eazibank.data.repositories.AccountRepository;
import com.nibss.eazibank.data.repositories.CustomerRepository;
import com.nibss.eazibank.dto.request.CreateCustomerRequest;
import com.nibss.eazibank.dto.request.CustomerDepositRequest;
import com.nibss.eazibank.dto.response.CustomerDepositResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;

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
        CreateCustomerRequest createCustomerRequest = new  CreateCustomerRequest("Aanuoluwa", "Ogundele",
                                            "07044388440", "", "");
        customerServices.createCustomer(createCustomerRequest);
        assertEquals(1, accountRepository.count());
        assertEquals(1, customerRepository.count());
    }

//    @Test
//    public void customerCanDepositTest(){
//        Customer customer = new Customer("Aanuoluwa", "Ogundele", "07044388440");
//        CustomerDepositRequest depositRequest = new CustomerDepositRequest("19837916342", BigInteger.valueOf(100_000_000));
//        CustomerDepositResponse depositResponse = customerServices.deposit(depositRequest);
//
//        assertEquals(1, accountRepository.count());
//        assertEquals(1, customerRepository.count());
//
//    }
}