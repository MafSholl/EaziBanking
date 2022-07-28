package com.nibss.eazibank.dto.request;

import lombok.*;

import java.math.BigInteger;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CreditAccountRequest {

    @NonNull
    private String accountNumber;
    @NonNull
    private BigInteger amount;
    private String narration;
}
