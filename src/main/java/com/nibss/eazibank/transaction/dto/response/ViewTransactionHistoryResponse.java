package com.nibss.eazibank.transaction.dto.response;

import com.nibss.eazibank.transaction.models.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewTransactionHistoryResponse {

    List<Transaction> transactionHistory;

}
