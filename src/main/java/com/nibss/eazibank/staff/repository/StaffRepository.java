package com.nibss.eazibank.staff.repository;

import com.nibss.eazibank.staff.models.Staff;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends MongoRepository<Staff, String>{

}
