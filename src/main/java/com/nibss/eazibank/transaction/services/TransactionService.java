package com.nibss.eazibank.transaction.services;

import com.nibss.eazibank.dto.CreateTransactionDto;
import com.nibss.eazibank.dto.request.ViewTransactionHistoryRequest;
import com.nibss.eazibank.dto.response.ViewTransactionHistoryResponse;

public interface TransactionService {

    void createAndSaveTransaction(CreateTransactionDto createTransactionDto);

    ViewTransactionHistoryResponse viewTransactionHistory(ViewTransactionHistoryRequest viewTransactionHistoryRequest);
}
