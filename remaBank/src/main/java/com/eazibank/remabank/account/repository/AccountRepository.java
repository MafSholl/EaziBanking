package com.eazibank.remabank.account.repository;

import com.eazibank.remabank.account.models.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

    Optional<Account> findByAccountNumber(String accountNumber);

    Optional<Account> findByFirstName(String firstName);
}
