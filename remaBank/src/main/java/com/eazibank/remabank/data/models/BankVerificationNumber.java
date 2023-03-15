package com.eazibank.remabank.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankVerificationNumber {
    private BigInteger bankVerificationNumber;
}
