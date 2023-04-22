package com.eazibank.egobank.transaction.dto;

import com.eazibank.egobank.atm.models.Location.Location;
import com.eazibank.egobank.transaction.models.PaymentMethod;
import com.eazibank.egobank.transaction.models.TransactionType;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class CreateTransactionDto {

    private String transactionId;
    private String BVN;
    private String accountNumber;
    private BigInteger amount;
    private String description;
    private TransactionType transactionType;
    private String recipientAccountNumber;
    private String recipientName;
    private PaymentMethod paymentMethod;
    private LocalDateTime transactionTime = LocalDateTime.now();
    private Location location;
}
