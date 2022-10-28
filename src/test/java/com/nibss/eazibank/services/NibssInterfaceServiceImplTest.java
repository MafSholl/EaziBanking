package com.nibss.eazibank.services;

import com.nibss.eazibank.data.repositories.NibssRepository;
import com.nibss.eazibank.dto.CreateBvnDto;
import com.nibss.eazibank.dto.NibssCustomerDto;
import com.nibss.eazibank.dto.request.RegisterAccountRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class NibssInterfaceServiceImplTest {

    @Autowired private NibssInterfaceService nIbssInterfaceService;
    private CreateBvnDto createBvnRequest;
    @Autowired private NibssRepository nibssRepository;

    @BeforeEach
    void setUp() {
        RegisterAccountRequest request = new RegisterAccountRequest("Adeola", "Ololade",
                "01-01-1991","08101234568", "Ayoola", "Afolabi", "savings");
        this.createBvnRequest = new CreateBvnDto(request);
    }

    @Test
    public void nibssInterfaceExistTest() {
        NibssInterfaceService nibssInterfaceService = new NibssInterfaceServiceImpl();
        assertNotNull(nibssInterfaceService);
    }

    @Test
    public void nibssInterfaceService_GeneratesBvnTest() {
        String bvn = nIbssInterfaceService.bvnGenerator(createBvnRequest).getBvn();
        assertEquals(String.valueOf(1_000_000_000), bvn);
    }

    @Test
    public void nibssInterface_HasNewBvnOnEachCallTest() {
        String bvn1 = nIbssInterfaceService.bvnGenerator(createBvnRequest).getBvn();
        String bvn2 = nIbssInterfaceService.bvnGenerator(createBvnRequest).getBvn();
        assertEquals(String.valueOf(1_000_000_000), bvn1);
        assertEquals(String.valueOf(1_000_000_001), bvn2);
    }

    @Test
    public void nibssInterface_CanCreateAndPersistNewCustomerTest() {
        assertEquals(0, nibssRepository.count());
        nIbssInterfaceService.bvnGenerator(createBvnRequest);
        assertEquals(1, nibssRepository.count());
    }

    @Test
    public void nibssPersistedCustomer_isWhatReturnedTest() {
        NibssCustomerDto newBankUser = nIbssInterfaceService.bvnGenerator(createBvnRequest);
    }
}