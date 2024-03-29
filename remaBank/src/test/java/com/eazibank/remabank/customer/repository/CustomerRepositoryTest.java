package com.eazibank.remabank.customer.repository;

import com.eazibank.remabank.RemaBankModuleConfig;
import com.eazibank.remabank.customer.models.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@ContextConfiguration(classes = RemaBankModuleConfig.class)
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MongoTemplate mongoTemplate;



    @Test
    public void repositoryTest() {
        Customer customer = new Customer();
        customerRepository.save(customer);
        assertEquals(1, customerRepository.count());
    }
    @Test
//    @Transactional
    public void whenPerformMongoTransaction_thenSuccess() {
        Customer customer1 = new Customer("lade", "fiyin", "0091929233");
        Customer customer2 = new Customer("toyin", "deleola", "9998876532");
        customerRepository.save(customer1);
        customerRepository.save(customer2);
        Query query = new Query().addCriteria(Criteria.where("name").is("lade"));
        List<Customer> customers = mongoTemplate.find(query, Customer.class);
        assertEquals(String.valueOf(customers.size()), String.valueOf(customerRepository.count()));
    }
}