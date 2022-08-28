package com.nibss.eazibank.data.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Document("Customer")
public class Customer {
    @Id
    @Indexed(unique = true)
    private String BVN;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull @Indexed(unique = true)
    private String phoneNumber;
    @Indexed(unique=true)
    @Email
    private String email;
    private LocalDateTime DOB;
    private String mothersMaidenName;
    private Map<String, Account> customerAccounts;
    private List<Transaction> transactionHistory;
}


