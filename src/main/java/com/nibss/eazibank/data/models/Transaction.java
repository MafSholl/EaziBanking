package com.nibss.eazibank.data.models;

import com.nibss.eazibank.data.models.enums.PaymentMethod;
import com.nibss.eazibank.data.models.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
        private String transactionId;
        private BigInteger amount;
        private String description;
        private TransactionType transactionType;
        private String beneficiaryAccountNumber;
        private String beneficiaryName;
        private PaymentMethod paymentMethod;
        private LocalDateTime transactionTime = LocalDateTime.now();
}
