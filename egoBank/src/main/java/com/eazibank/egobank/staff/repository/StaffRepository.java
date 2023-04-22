package com.eazibank.egobank.staff.repository;

import com.eazibank.egobank.staff.models.Staff;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends MongoRepository<Staff, String>{

}
