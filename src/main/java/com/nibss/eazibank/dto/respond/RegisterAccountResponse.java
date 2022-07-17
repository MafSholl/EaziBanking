package com.nibss.eazibank.dto.respond;

import lombok.Data;

import java.math.BigInteger;

@Data
public class RegisterAccountResponse {

    private String firstName;
    private String lastName;
    private String accountNumber;
//    private BigInteger balance;
    private String dateCreated;
}
