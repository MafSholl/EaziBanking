package com.nibss.eazibank.dto.response;

import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterAccountResponse {

    private String success;
    private String firstName;
    private String lastName;
    private String accountNumber;
    private BigInteger balance;
    private LocalDateTime accountCreationDate;
    private String bvn;
}
