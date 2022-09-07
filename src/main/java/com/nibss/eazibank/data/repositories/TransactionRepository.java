package com.nibss.eazibank.data.repositories;

import com.nibss.eazibank.data.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

}
