package com.nibss.eazibank.data.repositories;

import com.nibss.eazibank.data.models.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void repositoryTest() {
        Customer customer = new Customer();
        customerRepository.save(customer);
        assertEquals(1, customerRepository.count());
    }
}