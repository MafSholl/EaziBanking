package com.eazibank.remabank.customer.dto.response;

import lombok.*;

import java.math.BigInteger;
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class CreateCustomerResponse {
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull private String accountNumber;
    @NonNull private String accountType;
    @NonNull private BigInteger balance;
    @NonNull private String BVN;
    @NonNull private String email;
}
