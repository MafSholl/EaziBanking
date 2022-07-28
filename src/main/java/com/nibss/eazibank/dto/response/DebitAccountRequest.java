package com.nibss.eazibank.dto.response;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@Data
@RequiredArgsConstructor
public class DebitAccountRequest {

    @NonNull
    private String accountNumber;
    @NonNull
    private BigInteger amount;
    private String narration;
}
