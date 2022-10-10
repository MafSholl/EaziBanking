package com.nibss.eazibank.data.repositories;

import com.nibss.eazibank.data.models.BankVerificationNumber;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface NibssRepository extends MongoRepository<BigInteger, Integer> {
}
