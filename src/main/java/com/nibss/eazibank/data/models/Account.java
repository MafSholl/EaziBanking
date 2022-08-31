package com.nibss.eazibank.data.models;

import com.nibss.eazibank.data.models.enums.AccountType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("Account")
public class Account {
    @Id
    private String id;
    @NonNull
    @Indexed(unique = true)
    private String accountNumber;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String phoneNumber;
    private String email;
    private BigInteger balance = BigInteger.ZERO;
    private String bankVerificationNumber;
    private AccountType accountType;
    private LocalDateTime accountCreationDate = LocalDateTime.now();
}