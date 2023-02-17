package com.nibss.eazibank.transaction.dto;

import com.nibss.eazibank.data.models.enums.Location.Location;
import com.nibss.eazibank.data.models.enums.PaymentMethod;
import com.nibss.eazibank.data.models.enums.TransactionType;
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
