package com.eazibank.nibss.services;

import com.eazibank.nibss.dto.response.NibssBankUserDto;
import com.eazibank.nibss.dto.response.NibssInterbankDto;
import com.eazibank.nibss.models.Account;
import com.eazibank.nibss.models.CreateBvnDto;
import com.eazibank.nibss.models.NibssBankUser;
import com.eazibank.nibss.repository.NibssRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service
public class NibssInterfaceServiceImpl implements NibssInterfaceService {
    @Autowired
    private NibssRepository nibssRepository;
    @Autowired
    private ModelMapper modelMapper;

    private static BigInteger bvnNumber = BigInteger.valueOf(1_000_000_000);
    public NibssBankUserDto bvnGenerator(CreateBvnDto createBvnRequest) {
        Map<String, List<Account>> bankerInformation = new HashMap<>();
        List<Account> bankUserAccounts = new ArrayList<>();
        Account account = modelMapper.map(createBvnRequest, Account.class);
        account.setBvn(String.valueOf(bvnNumber));
        bankUserAccounts.add(account);
        bankerInformation.put(String.valueOf(createBvnRequest.getBankId()), bankUserAccounts);

        NibssBankUser nibssBankUser = NibssBankUser.builder()
                .bvn(String.valueOf(bvnNumber))
                .firstName(createBvnRequest.getFirstName())
                .lastName(createBvnRequest.getLastName())
                .userBankInformation(bankerInformation)
                .build();
        nibssRepository.save(nibssBankUser);
        bvnNumber = bvnNumber.add(BigInteger.valueOf(1));
        return modelMapper.map(nibssBankUser, NibssBankUserDto.class);
    }

    public boolean isNibssAvailable() {
        return true;
    }

    public NibssBankUserDto nibssInterbankDeposit(NibssInterbankDto nibssInterbankRequest) {
        //check if user exist
        Optional<NibssBankUser> bankUser = nibssRepository.findByBankUserAccountNumber(nibssInterbankRequest.getAccountNumber());
        //then call the second bank end point for a deposit
        return new NibssBankUserDto();
    }
}
