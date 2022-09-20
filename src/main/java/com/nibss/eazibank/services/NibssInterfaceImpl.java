package com.nibss.eazibank.services;

import java.math.BigInteger;

public class  NibssInterfaceImpl implements NibssInterface {

    private static BigInteger bvnNumber = BigInteger.valueOf(1_000_000_000);
    public String bvnGenerator() {
        String localBvnNumber = String.valueOf(bvnNumber);
        bvnNumber = bvnNumber.add(BigInteger.valueOf(1));
        return localBvnNumber;
    }

    public boolean isNibssAvailable() {
        return true;
    }
}
