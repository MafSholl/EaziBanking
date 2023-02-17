package com.nibss.eazibank.customer.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
@Data
@NoArgsConstructor
public class CustomerTransferResponse {

    private String firstName;
    private String lastName;
    private BigInteger amount;
    private String accountNumber;
    private boolean isSuccessful;
    private String message;
}
