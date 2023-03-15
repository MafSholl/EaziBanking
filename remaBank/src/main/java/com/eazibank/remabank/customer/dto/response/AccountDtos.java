package com.eazibank.remabank.customer.dto.response;

import com.eazibank.remabank.data.models.enums.AccountType;
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
