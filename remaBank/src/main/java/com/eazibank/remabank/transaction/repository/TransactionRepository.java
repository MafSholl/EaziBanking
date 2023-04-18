package com.eazibank.remabank.transaction.repository;

import com.eazibank.remabank.transaction.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

    List<Transaction> findByAccountNumber(String accountNumber);
}
