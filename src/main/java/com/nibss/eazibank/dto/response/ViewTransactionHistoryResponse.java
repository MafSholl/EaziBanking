package com.nibss.eazibank.dto.response;

import com.nibss.eazibank.data.models.Transaction;
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
