package com.nibss.eazibank.nibss.dto.response;

import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NibssInterbankDto {

    private String accountNumber;
    private BigInteger amount;
    private String firstName;
    private String lastName;
    private String bankId;
    private String bank;
}
