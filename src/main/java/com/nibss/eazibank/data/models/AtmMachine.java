package com.nibss.eazibank.data.models;

import com.nibss.eazibank.data.models.enums.Location.Location;
import com.nibss.eazibank.transaction.models.Transaction;

import java.util.List;

public class AtmMachine {

    private String id;
    private String name;
    private String version;
    private Location location;
    private LogHistory logHistory;
    private List<Transaction> transactionHistory;

}
