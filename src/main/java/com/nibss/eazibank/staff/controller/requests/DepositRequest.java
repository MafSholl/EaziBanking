package com.nibss.eazibank.staff.controller.requests;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class DepositRequest {

    @NonNull
    private String accountNumber;
    private String firstName;
    private String lastName;
    @NonNull
    private BigInteger amount;
    private String narration;
}
