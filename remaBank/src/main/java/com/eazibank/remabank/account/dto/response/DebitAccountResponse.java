package com.eazibank.remabank.account.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class DebitAccountResponse {

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
