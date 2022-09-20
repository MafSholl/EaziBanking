package com.nibss.eazibank.data.models;

import com.nibss.eazibank.services.NibssInterface;

import java.util.HashMap;
import java.util.List;

public class BankVerificationNumber {
    private final String ID;
    private NibssInterface nibssInterface;

    public BankVerificationNumber() {
        this.ID = nibssInterface.bvnGenerator();
    }
}
