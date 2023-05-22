package com.eazibank.remabank.account.models;

import com.eazibank.remabank.transaction.models.Transaction;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Document("Account")
public class Account {
    @Id @NonNull
    @Indexed(unique = true)
    private String accountNumber;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String phoneNumber;
    private String email;
    private String mothersMaidenName;
    private BigInteger balance = BigInteger.ZERO;
    private String bvn;
    private AccountType accountType;
    @DBRef
    private List<Transaction> transactionHistory;
    private LocalDateTime accountCreationDate = LocalDateTime.now();
}