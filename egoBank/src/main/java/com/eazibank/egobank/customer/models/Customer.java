package com.eazibank.egobank.customer.models;

import com.eazibank.egobank.account.models.Account;
import com.eazibank.egobank.transaction.models.Transaction;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Document("Customer")
public class Customer {
    @Id
    @Indexed(unique = true)
    private String bvn;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull @Indexed(unique = true)
    private String phoneNumber;
    @Indexed(unique=true)
    @Email
    private String email;
    private LocalDateTime dateJoined = LocalDateTime.now();
    private LocalDateTime DOB;
    private String mothersMaidenName;
    private Map<String, Account> customerAccounts;
    @DBRef
    private List<Transaction> transactionHistory = new ArrayList<>();
    private String password;
    private String confirmPassword;
}


