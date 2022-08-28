package com.nibss.eazibank.data.models;

import com.nibss.eazibank.services.NibssInterface;
import com.nibss.eazibank.services.NibssInterfaceImpl;

import java.util.HashMap;
import java.util.List;

public class BankVerificationNumber {

    private String firstName;
    private String lastName;
    private final String ID;
    private HashMap<Bank, List<Account>> accounts;

    public BankVerificationNumber() {
        this.ID = NibssInterfaceImpl.bvnGenerator();
    }
}
