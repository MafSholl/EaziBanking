package com.nibss.eazibank.nibss.repository;

import com.nibss.eazibank.nibss.models.NibssBankUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NibssRepository extends MongoRepository<NibssBankUser, Integer> {

    Optional<NibssBankUser> findByBvn(String bvn);

    @Query(value = "{'userBankInformation.?.bankUserAccounts.account.accountNumber': ?0}")
    Optional<NibssBankUser> findByBankUserAccountNumber(String accountNumber);
}
