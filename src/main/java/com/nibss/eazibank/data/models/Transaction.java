package com.nibss.eazibank.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
//@RequiredArgsConstructor
//@AllArgsConstructor
public class Transaction {
        private BigInteger amount;
        private String description;
        private String beneficiaryAccountNumber;
        private String beneficiaryName;
        private String paymentMethod;
        private String sessionID;
        private LocalDateTime transactionTime = LocalDateTime.now();
}
