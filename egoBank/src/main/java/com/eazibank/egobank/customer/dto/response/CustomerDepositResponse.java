package com.eazibank.egobank.customer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDepositResponse {

    private String firstName;
    private String lastName;
    private String accountNumber;
    private BigInteger amount;
    private boolean success;

}
