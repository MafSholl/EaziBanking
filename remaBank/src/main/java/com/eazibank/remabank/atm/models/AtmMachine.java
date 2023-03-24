package com.eazibank.remabank.atm.models;

import com.eazibank.remabank.atm.models.Location.Location;
import com.eazibank.remabank.transaction.models.Transaction;

import java.util.List;

public class AtmMachine {

    private String id;
    private String name;
    private String version;
    private Location location;
    private AtmLog atmLogHistory;
    private List<Transaction> transactionHistory;

}
