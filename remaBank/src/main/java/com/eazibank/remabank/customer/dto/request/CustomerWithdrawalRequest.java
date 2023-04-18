package com.eazibank.remabank.customer.dto.request;

import lombok.Data;
import lombok.NonNull;

import java.math.BigInteger;
@Data
public class CustomerWithdrawalRequest {

    private String firstName;
    private String lastName;
    @NonNull
    private String accountNumber;
    @NonNull
    private BigInteger amount;
    private String description;
}
