package com.nibss.eazibank.data.repositories;

import com.nibss.eazibank.data.models.NibssBankUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface NibssRepository extends MongoRepository<NibssBankUser, Integer> {
}
