package com.eazibank.remabank.staff.repository;

import com.eazibank.remabank.staff.models.Staff;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends MongoRepository<Staff, String>{
}
