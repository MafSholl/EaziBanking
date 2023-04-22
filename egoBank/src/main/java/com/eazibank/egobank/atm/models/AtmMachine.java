package com.eazibank.egobank.atm.models;

import com.eazibank.egobank.atm.models.Location.Location;
import com.eazibank.egobank.transaction.models.Transaction;

import java.util.List;

public class AtmMachine {

    private String id;
    private String name;
    private String version;
    private Location location;
    private AtmLog atmLogHistory;
    private List<Transaction> transactionHistory;

}
