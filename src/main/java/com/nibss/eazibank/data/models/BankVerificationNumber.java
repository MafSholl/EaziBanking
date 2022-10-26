package com.nibss.eazibank.data.models;

import com.nibss.eazibank.services.NibssInterface;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankVerificationNumber {
    private BigInteger bankVerificationNumber;
}
