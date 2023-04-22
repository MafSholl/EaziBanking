package com.eazibank.egobank.account.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigInteger;
@Data
@NoArgsConstructor
public class CreditAccountResponse {
    @NonNull
    private boolean isSuccessful;
    private BigInteger balance;
    private String accountNumber;
    private String bankVerificationNumber;
    private String firstName;
    private String lastName;
}
