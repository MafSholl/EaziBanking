package com.nibss.eazibank.dto.response;

import lombok.*;

import java.math.BigInteger;

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
    private String accountCreationDate;
    private String bankVerificationNumber;
}
