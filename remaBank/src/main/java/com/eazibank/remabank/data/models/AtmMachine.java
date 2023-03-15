package com.eazibank.remabank.data.models;

import com.eazibank.remabank.data.models.enums.Location.Location;
import com.eazibank.remabank.transaction.models.Transaction;

import java.util.List;

public class AtmMachine {

    private String id;
    private String name;
    private String version;
    private Location location;
    private LogHistory logHistory;
    private List<Transaction> transactionHistory;

}
