package com.nibss.eazibank.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigInteger;
@Data
@AllArgsConstructor
public class CreditAccountRequest {

    @NonNull
    private String accountNumber;
    @NonNull
    private BigInteger amount;
}
