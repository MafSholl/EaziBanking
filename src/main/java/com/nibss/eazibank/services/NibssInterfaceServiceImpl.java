package com.nibss.eazibank.services;

import com.nibss.eazibank.data.models.BankVerificationNumber;
import com.nibss.eazibank.data.repositories.NibssRepository;
import com.nibss.eazibank.dto.CreateBvnDto;
import com.nibss.eazibank.dto.NibssCustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
@Service
public class NibssInterfaceServiceImpl implements NibssInterfaceService {
    @Autowired
    private NibssRepository nibssRepository;

    private static BigInteger bvnNumber = BigInteger.valueOf(1_000_000_000);
    public NibssCustomerDto bvnGenerator(CreateBvnDto createBvnDto) {
        String returnedBvnNumber = String.valueOf(bvnNumber);
        nibssRepository.save(new BankVerificationNumber(bvnNumber));
        bvnNumber = bvnNumber.add(BigInteger.valueOf(1));
        return new NibssCustomerDto();
    }

    public boolean isNibssAvailable() {
        return true;
    }
}
