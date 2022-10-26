package com.nibss.eazibank.data.repositories;

import com.nibss.eazibank.data.models.BankVerificationNumber;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
@Repository
public interface NibssRepository extends MongoRepository<BankVerificationNumber, Integer> {
}
