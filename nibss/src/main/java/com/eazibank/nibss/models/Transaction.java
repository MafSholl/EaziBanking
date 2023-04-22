package com.eazibank.nibss.models;

import com.eazibank.nibss.models.Location.Location;
import com.eazibank.nibss.models.enums.PaymentMethod;
import com.eazibank.nibss.models.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "Transactions")
public class Transaction {
        @Id
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
