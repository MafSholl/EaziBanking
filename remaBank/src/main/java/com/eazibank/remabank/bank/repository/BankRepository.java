package com.eazibank.remabank.bank.repository;

import com.eazibank.remabank.bank.models.Bank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends MongoRepository<Bank, String>{
}
