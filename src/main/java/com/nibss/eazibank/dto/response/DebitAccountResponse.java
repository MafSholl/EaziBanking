package com.nibss.eazibank.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class DebitAccountResponse {

    @NonNull
    private boolean isSuccessful;
    @NonNull
    private String accountNumber;
    private String firstName;
    private String lastName;
    @NonNull
    private BigInteger debitedAmount;
    @NonNull
    private BigInteger balance;
}
