package com.nibss.eazibank.data.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
//@Document("Bank")
public class Bank {
    @Id
    @Indexed(unique = true)
    private int id;
    private String name;
    private BigDecimal balance;
    private List<Director> boardOfDirectors;
    private List<Shareholder> shareholders;
    private List<Staff> staffs = new ArrayList<>();
    @DBRef
    private HashMap<String, Customer> customers = new HashMap<>();
    private HashMap<String, AtmMachine> atmMachines;
}
