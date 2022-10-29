package com.nibss.eazibank.dto;

import com.nibss.eazibank.data.models.enums.AccountType;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDtos {
    private String id;
    private String accountNumber;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private BigInteger balance = BigInteger.ZERO;
    private String bvn;
    private AccountType accountType;
    private LocalDateTime accountCreationDate = LocalDateTime.now();
}
