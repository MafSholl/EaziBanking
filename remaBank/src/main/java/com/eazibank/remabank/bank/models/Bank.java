package com.eazibank.remabank.bank.models;

import com.eazibank.remabank.atm.models.AtmMachine;
import com.eazibank.remabank.customer.models.Customer;
import com.eazibank.remabank.staff.models.Staff;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("Bank")
public class Bank {
    @Id
    @Indexed(unique = true)
    private String id = "01";
    private String name = "Rema bank";
    private BigDecimal balance = new BigDecimal(50_000_000_000L);
    private List<Director> boardOfDirectors;
    private List<Shareholder> shareholders;
    @DBRef
    private List<Staff> staffs = new ArrayList<>();
    @DBRef
    private HashMap<String, Customer> customers = new HashMap<>();
    private HashMap<String, AtmMachine> atmMachines;
}
