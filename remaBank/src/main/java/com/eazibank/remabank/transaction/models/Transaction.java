package com.eazibank.remabank.transaction.models;

import com.eazibank.remabank.atm.models.Location.Location;
import com.eazibank.remabank.transaction.models.enums.PaymentMethod;
import com.eazibank.remabank.transaction.models.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@Document(collection = "Transaction")
public class Transaction {
        @Id
        private String transactionId;
        private String BVN;
        private String accountNumber;
        private BigInteger amount;
        private String description;
        private String recipientAccountNumber;
        private String recipientName;
        private TransactionType transactionType;
        @Builder.Default
        private LocalDateTime transactionTime = LocalDateTime.now();
        private PaymentMethod paymentMethod;
        private Location location;
}
