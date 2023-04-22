package com.eazibank.egobank.customer.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CustomerTransferRequest {

    private String firstName;
    private String lastName;
    @NonNull
    private String receiversAccountNumber;
    @NonNull
    private String sendersAccountNumber;
    @NonNull
    private BigInteger amount;
    private String narration;
}
