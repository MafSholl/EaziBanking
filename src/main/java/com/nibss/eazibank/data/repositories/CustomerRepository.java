package com.nibss.eazibank.data.repositories;

import com.nibss.eazibank.data.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String>{

    Optional<Customer> findByFirstName(String firstName);
//    Optional<Customer> findByCustomerAccounts(Map<String, Account> customerAccounts);
    Optional<Customer> findCustomerByBVN(String bvn);

    Optional<Customer> findCustomerByCustomerAccountsContaining(String accountNumber);
}