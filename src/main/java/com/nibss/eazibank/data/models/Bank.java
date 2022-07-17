package com.nibss.eazibank.data.models;

import lombok.Data;

import java.util.HashMap;

@Data
public class Bank {
    private String identityNumber;
    private String name;
    private String balance;
//    private HashMap<Customer, HashMap<>Portfolio> users;
//    private HashMap<Customer, Account> portfolio;
}
