package com.nibss.eazibank.data.repositories;

import com.nibss.eazibank.data.models.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

    Account findByAccountNumber(String accountNumber);
}
