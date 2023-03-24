package com.nibss.eazibank.nibss.services;

import com.nibss.eazibank.account.dto.response.CreateBvnDto;
import com.nibss.eazibank.account.models.Account;
import com.nibss.eazibank.data.models.enums.AccountType;
import com.nibss.eazibank.nibss.dto.response.NibssBankUserDto;
import com.nibss.eazibank.nibss.dto.response.NibssInterbankDto;
import com.nibss.eazibank.nibss.models.NibssBankUser;
import com.nibss.eazibank.nibss.repository.NibssRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class NibssInterfaceServiceImplTest {

    @Autowired private NibssInterfaceService nibssInterfaceService;
    private CreateBvnDto createBvnDto;
    @Autowired private NibssRepository nibssRepository;

    @BeforeEach
    void setUp() {
        Account account = Account.builder()
                .firstName("Adeola")
                .lastName("Erujeje")
                .phoneNumber("08101234568")
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
        String bvn = nibssInterfaceService.bvnGenerator(createBvnDto).getBvn();
        assertEquals(String.valueOf(1_000_000_000), bvn);
    }

    @Test
    public void nibssInterface_HasNewBvnOnEachCallTest() {
        String bvn1 = nibssInterfaceService.bvnGenerator(createBvnDto).getBvn();
        String bvn2 = nibssInterfaceService.bvnGenerator(createBvnDto).getBvn();
        assertEquals(String.valueOf(1_000_000_000), bvn1);
        assertEquals(String.valueOf(1_000_000_001), bvn2);
    }

    @Test
    public void nibssInterface_CanCreateAndPersistNewCustomerTest() {
        assertEquals(0, nibssRepository.count());
        nibssInterfaceService.bvnGenerator(createBvnDto);
        assertEquals(1, nibssRepository.count());
    }

    @Test
    public void nibssPersistedCustomer_isWhatsReturnedTest() {
        NibssBankUserDto newBankUser = nibssInterfaceService.bvnGenerator(createBvnDto);
        assertEquals(createBvnDto.getFirstName(), newBankUser.getFirstName());
        assertEquals(createBvnDto.getLastName(), newBankUser.getLastName());
        assertEquals("1000000000", newBankUser.getBvn());
    }

    @Test
    public void nibssCanPerformInterbankTransferTest() {
        Account account_1 = Account.builder()
                .firstName("Adeola")
                .lastName("Erujeje")
                .phoneNumber("08101234568")
                .email("")
                .accountNumber("1234567890")
                .balance(BigInteger.ZERO)
                .accountType(AccountType.SAVINGS)
                .build();
        Account account_2 = new Account();
        account_2.setFirstName("Joshua");
        account_2.setLastName("Kekere-ekun");
        account_2.setPhoneNumber("07012345678");
        account_2.setAccountType(AccountType.SAVINGS);
        account_2.setAccountNumber("0123456789");
        account_2.setEmail("");

        //First I created 2 nibss bank users
        CreateBvnDto createBvnDto1 = new CreateBvnDto(account_1);
        createBvnDto1.setBankId(1L);
        CreateBvnDto createBvnDto2 = new CreateBvnDto(account_2);
        createBvnDto2.setBankId(2L);
        NibssBankUserDto bankUser1 = nibssInterfaceService.bvnGenerator(createBvnDto1);
        NibssBankUserDto bankUser2 = nibssInterfaceService.bvnGenerator(createBvnDto2);

        //Next I queried for the 2nd bank user that'll be credited, save the balance before credit alert
        NibssBankUser savedBankUser2 = nibssRepository.findByBvn(bankUser2.getBvn()).get();
        Map<String, List<Account>> user2BankerInformation = savedBankUser2.getUserBankInformation();
        List<Account> bankUserAccounts = user2BankerInformation.get("2");
        Account accountReceivingTransfer = bankUserAccounts.get(0);
        for (Account account : bankUserAccounts) {
            if (account.getAccountNumber().equals(account_2.getAccountNumber())) {
                accountReceivingTransfer = account;
            }
        }
        BigInteger balanceBeforeTransferToAcc = accountReceivingTransfer.getBalance();

        NibssInterbankDto transferRequest = NibssInterbankDto.builder()
                .firstName("Joshua")
                .lastName("Kekere-ekun")
                .accountNumber("0123456789")
                .amount(new BigInteger("15000"))
                .bank("KaboBank")
                .bankId("2")
                .build();
        NibssBankUserDto transferResponse = nibssInterfaceService.nibssInterbankDeposit(transferRequest);

        //Then I queried for the same bank user, save the new balance before credit alert
        NibssBankUser creditedBankUser = nibssRepository.findByBvn(bankUser2.getBvn()).get();
        List<Account> accounts = creditedBankUser.getUserBankInformation().get("2");
        Account accountThatReceivedTransfer = accounts.get(0);
        for (Account account : accounts) {
            if(account.getAccountNumber().equals(account_2.getAccountNumber())) {
                accountThatReceivedTransfer = account;
            }
        }
        BigInteger balanceAfterTransfer = accountThatReceivedTransfer.getBalance();

        assertThat(transferResponse.getBvn()).isEqualTo(bankUser2.getBvn());
        assertThat(balanceAfterTransfer).isEqualTo(balanceBeforeTransferToAcc.add(transferRequest.getAmount()));
    }
}