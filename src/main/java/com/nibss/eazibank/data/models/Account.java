package com.nibss.eazibank.data.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Document("Account")
public class Account {
    @Id
    private String id;
    @NonNull @Indexed(unique = true)
    private String accountNumber;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String phoneNumber;
    private String email;
    private BigInteger balance = BigInteger.ZERO;
    private LocalDateTime accountCreationDate = LocalDateTime.now();


}