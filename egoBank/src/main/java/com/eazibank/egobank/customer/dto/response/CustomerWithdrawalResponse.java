package com.eazibank.egobank.customer.dto.response;

import lombok.Data;

import java.math.BigInteger;

@Data
public class CustomerWithdrawalResponse {

    private String firstName;
    private String lastName;
    private BigInteger amount;
    private boolean isSuccessful;
}
