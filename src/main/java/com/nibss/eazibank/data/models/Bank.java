package com.nibss.eazibank.data.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("Bank")
public class Bank {
    private String identityNumber;
    private String name;
    private String balance;
//    private List<BoardOfDirectors> boardOfDirectors;
//    private List<Shareholder> shareholders;
    private List<Staff> staffs = new ArrayList<>();
    private HashMap<String, Customer> customers;
    private HashMap<String, AtmMachine> atmMachines;
}
