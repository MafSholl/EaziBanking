package com.eazibank.remabank.bank.models;

import com.eazibank.remabank.customer.models.Customer;
import com.eazibank.remabank.data.models.AtmMachine;
import com.eazibank.remabank.staff.models.Staff;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
    @Builder.Default
    private List<Staff> staffs = new ArrayList<>();
    @Builder.Default
    @DBRef
    private HashMap<String, Customer> customers = new HashMap<>();
    private HashMap<String, AtmMachine> atmMachines;
}
