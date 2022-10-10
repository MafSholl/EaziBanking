package com.nibss.eazibank.dto.response;

import lombok.*;

import java.math.BigInteger;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StaffDepositDto {
    private String firstName;
    private String lastName;
    private String accountNumber;
    private BigInteger amount;
    private boolean success;
    private String staffId;
}
