package com.nibss.eazibank.services;

import com.nibss.eazibank.data.models.Account;
import com.nibss.eazibank.data.models.enums.AccountType;
import com.nibss.eazibank.data.repositories.NibssRepository;
import com.nibss.eazibank.dto.CreateBvnDto;
import com.nibss.eazibank.dto.NibssBankUserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class NibssInterfaceServiceImplTest {

    @Autowired private NibssInterfaceService nIbssInterfaceService;
    private CreateBvnDto createBvnDto;
    @Autowired private NibssRepository nibssRepository;

    @BeforeEach
    void setUp() {
        Account account = Account.builder()
                .firstName("Adeola")
                .lastName("Erujeje")
                .phoneNumber("081w01234568")
                .email("")
                .accountNumber("1234567890")
                .balance(BigInteger.ZERO)
                .accountType(AccountType.SAVINGS)
                .build();
        this.createBvnDto = new CreateBvnDto(account);
    }

    @Test
    public void nibssInterfaceExistTest() {
        NibssInterfaceService nibssInterfaceService = new NibssInterfaceServiceImpl();
        assertNotNull(nibssInterfaceService);
    }

    @Test
    public void nibssInterfaceService_GeneratesBvnTest() {
        String bvn = nIbssInterfaceService.bvnGenerator(createBvnDto).getBvn();
        assertEquals(String.valueOf(1_000_000_000), bvn);
    }

    @Test
    public void nibssInterface_HasNewBvnOnEachCallTest() {
        String bvn1 = nIbssInterfaceService.bvnGenerator(createBvnDto).getBvn();
        String bvn2 = nIbssInterfaceService.bvnGenerator(createBvnDto).getBvn();
        assertEquals(String.valueOf(1_000_000_000), bvn1);
        assertEquals(String.valueOf(1_000_000_001), bvn2);
    }

    @Test
    public void nibssInterface_CanCreateAndPersistNewCustomerTest() {
        assertEquals(0, nibssRepository.count());
        nIbssInterfaceService.bvnGenerator(createBvnDto);
        assertEquals(1, nibssRepository.count());
    }

    @Test
    public void nibssPersistedCustomer_isWhatsReturnedTest() {
        NibssBankUserDto newBankUser = nIbssInterfaceService.bvnGenerator(createBvnDto);
        assertEquals(createBvnDto.getFirstName(), newBankUser.getFirstName());
        assertEquals(createBvnDto.getLastName(), newBankUser.getLastName());
        assertEquals("1000000000", newBankUser.getBvn());
    }
}