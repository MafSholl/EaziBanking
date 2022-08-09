package com.nibss.eazibank.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
public class RegisterAccountResponse {

    private String firstName;
    private String lastName;
    private String accountNumber;
    private BigInteger balance;
    private String dateCreated;
    private String bankVerificationNumber;
}
