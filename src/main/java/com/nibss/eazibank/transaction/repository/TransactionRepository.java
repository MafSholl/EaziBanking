package com.nibss.eazibank.transaction.repository;

import com.nibss.eazibank.transaction.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

    List<Transaction> findByAccountNumber(String accountNumber);
}
