package com.nibss.eazibank.data.repositories;

import com.nibss.eazibank.staff.models.Staff;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends MongoRepository<Staff, String>{

}
