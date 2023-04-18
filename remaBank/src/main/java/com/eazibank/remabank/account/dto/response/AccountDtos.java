package com.eazibank.remabank.account.dto.response;

import com.eazibank.remabank.account.models.AccountType;
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
    @Builder.Default
    private BigInteger balance = BigInteger.ZERO;
    private String bvn;
    private AccountType accountType;
    @Builder.Default
    private LocalDateTime accountCreationDate = LocalDateTime.now();
}
