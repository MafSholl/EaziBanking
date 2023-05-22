package com.eazibank.remabank.bank.services;

import com.eazibank.remabank.RemaBankModuleConfig;
import com.eazibank.remabank.account.repository.AccountRepository;
import com.eazibank.remabank.bank.models.Bank;
import com.eazibank.remabank.customer.dto.request.CreateCustomerRequest;
import com.eazibank.remabank.customer.dto.response.CreateCustomerResponse;
import com.eazibank.remabank.customer.models.Customer;
import com.eazibank.remabank.customer.repository.CustomerRepository;
import com.eazibank.remabank.customer.services.CustomerServices;
import com.eazibank.remabank.exception.exceptions.BankDoesNotExistException;
import com.eazibank.remabank.staff.controller.requests.CreateStaffRequest;
import com.eazibank.remabank.staff.repository.StaffRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = RemaBankModuleConfig.class)
class BankServicesImplTest {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private CustomerServices customerServices;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    private Bank bank = Bank.builder()
            .name("EaziBank")
            .id("1")
            .balance(BigDecimal.valueOf(1000000000))
            .build();
    @Autowired
    private BankServices bankServices = new BankServicesImpl(bank);

    @Test
    public void bankExistTest() {
        Bank bank = new Bank();
        assertNotNull(bank);
    }

    @Test
    public void bankCanAddStaffTest() {
        CreateStaffRequest createStaffRequest = new CreateStaffRequest("Arewa", "Ijaodola",
                "0908272748", "2001-01-01", "single");
        bankServices.createStaff(createStaffRequest);
        assertEquals(1, staffRepository.count());
    }

    @Test
    public void bankMustExistBeforeStaffIsCreatedTest() {
        CreateStaffRequest createStaffRequest = new CreateStaffRequest("Arewa", "Ijaodola",
                "0908272748", "2001-01-01", "single");
        BankServices bankServices1 = new BankServicesImpl(bank);
        assertThrows(BankDoesNotExistException.class, ()-> bankServices.createStaff(createStaffRequest));
    }

    private void createStaff() {
        CreateStaffRequest createStaffRequest = new CreateStaffRequest("Arewa", "Ijaodola",
                "0908272748", "2001-01-01", "single");
        bankServices.createStaff(createStaffRequest);
        assertEquals(1, staffRepository.count());
    }

    @Test
    public void bankCanAddMoreThanOneStaffTest() {
        createStaff();
        CreateStaffRequest createStaffRequest = new CreateStaffRequest("Arewa", "Ijaodola",
                "0908272748", "2001-01-01", "single");
        bankServices.createStaff(createStaffRequest);
        assertEquals(2, staffRepository.count());
    }

    @Test
    public void staffCanCreateACustomerTest() {
        createStaff();
        CreateCustomerRequest createCustomerRequest = new  CreateCustomerRequest("Ayobaye", "Ogundele",
                "07048847840", "", "",
                "2000-01-20 00:00", "SAVINGS");
        CreateCustomerResponse createdCustomer = bankServices.createCustomer(createCustomerRequest);
        assertEquals(1, accountRepository.count());
        assertEquals(1, customerRepository.count());
    }

    private CreateCustomerResponse createStaffAndCustomer() {
        createStaff();
        CreateCustomerRequest createCustomerRequest = new  CreateCustomerRequest("Ayobaye", "Ogundele",
                "07048847840", "ayobaye@example.com", "",
                "2000-01-20 00:00", "SAVINGS");
        return bankServices.createCustomer(createCustomerRequest);
    }

    @Test
    public void staffCreatedIsWhatIsReturnedTest() {
        CreateCustomerResponse createCustomerResponse = createStaffAndCustomer();
        Customer customerInRepository = customerRepository.findCustomerByEmail(createCustomerResponse.getEmail()).get();
        assertEquals(createCustomerResponse.getBVN(), customerInRepository.getBvn());
    }

    @Test
    public void bankCanCallNibssInterfaceTest() {

        assertTrue(bankServices.isNibssInterfaceAvailable());
    }


}