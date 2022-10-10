package com.nibss.eazibank.services;

import com.nibss.eazibank.data.repositories.NibssRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
@Service
public class  NibssInterfaceImpl implements NibssInterface {
    @Autowired
    private NibssRepository nibssRepository;

    private static BigInteger bvnNumber = BigInteger.valueOf(1_000_000_000);
    public String bvnGenerator() {
        String returnedBvnNumber = String.valueOf(bvnNumber);
        nibssRepository.save(bvnNumber);
        bvnNumber = bvnNumber.add(BigInteger.valueOf(1));
        return returnedBvnNumber;
    }

    public boolean isNibssAvailable() {
        return true;
    }
}
