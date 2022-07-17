package com.nibss.eazibank.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@Data
@Document
public class Customer {
    private String BVN;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private HashMap<String, Account> customerAccounts;
}
