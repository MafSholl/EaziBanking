package com.nibss.eazibank.transaction.services;

import com.nibss.eazibank.transaction.dto.CreateTransactionDto;
import com.nibss.eazibank.transaction.dto.request.ViewTransactionHistoryRequest;
import com.nibss.eazibank.transaction.dto.response.ViewTransactionHistoryResponse;

public interface TransactionService {

    void createAndSaveTransaction(CreateTransactionDto createTransactionDto);

    ViewTransactionHistoryResponse viewTransactionHistory(ViewTransactionHistoryRequest viewTransactionHistoryRequest);
}
