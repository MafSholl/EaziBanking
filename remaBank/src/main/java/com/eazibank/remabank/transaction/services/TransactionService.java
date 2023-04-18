package com.eazibank.remabank.transaction.services;

import com.eazibank.remabank.transaction.dto.CreateTransactionDto;
import com.eazibank.remabank.transaction.dto.request.ViewTransactionHistoryRequest;
import com.eazibank.remabank.transaction.dto.response.ViewTransactionHistoryResponse;

public interface TransactionService {

    void createAndSaveTransaction(CreateTransactionDto createTransactionDto);

    ViewTransactionHistoryResponse viewTransactionHistory(ViewTransactionHistoryRequest viewTransactionHistoryRequest);
}
