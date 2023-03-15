package com.eazibank.remabank.bank.repository;

import com.eazibank.remabank.bank.models.Bank;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BankRepository extends MongoRepository<Bank, String>{
}
