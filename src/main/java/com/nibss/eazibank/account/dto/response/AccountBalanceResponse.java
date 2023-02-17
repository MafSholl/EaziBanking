package com.nibss.eazibank.account.dto.response;

import lombok.Data;

import java.math.BigInteger;

@Data
public class AccountBalanceResponse {

    private String firstName;
    private String lastName;
    private BigInteger balance;
}
