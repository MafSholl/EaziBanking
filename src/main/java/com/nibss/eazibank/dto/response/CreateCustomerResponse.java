package com.nibss.eazibank.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigInteger;
@Data
@NoArgsConstructor
public class CreateCustomerResponse {
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull private String accountNumber;
    @NonNull private String accountType;
    @NonNull private BigInteger balance;
    @NonNull private String BVN;
}
