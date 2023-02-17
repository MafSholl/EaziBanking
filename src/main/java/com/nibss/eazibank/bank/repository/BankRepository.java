package com.nibss.eazibank.bank.repository;

import com.nibss.eazibank.bank.models.Bank;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BankRepository extends MongoRepository<Bank, String>{
}
