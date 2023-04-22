package com.eazibank.egobank.bank.repository;

import com.eazibank.egobank.bank.models.Bank;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BankRepository extends MongoRepository<Bank, String>{
}
