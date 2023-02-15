package com.nibss.eazibank.data.repositories;

import com.nibss.eazibank.bank.models.Bank;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BankRepository extends MongoRepository<Bank, String>{
}
