package com.nibss.eazibank.data.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class Bank {
    private String identityNumber;
    private String name;
    private String balance;
    private List<BoardOfDirectors> boardOfDirectors;
    private List<Shareholder> shareholders;
    private List<Staff> staffs;
    private HashMap<BankVerificationNumber, Customer> customers;
    private HashMap<String, AtmMachine> atmMachines;
    public Bank() {
        this.staffs = new ArrayList<>();
    }
}
