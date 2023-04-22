package com.eazibank.egobank.transaction.services;

import com.eazibank.egobank.transaction.dto.CreateTransactionDto;
import com.eazibank.egobank.transaction.dto.request.ViewTransactionHistoryRequest;
import com.eazibank.egobank.transaction.dto.response.ViewTransactionHistoryResponse;

public interface TransactionService {

    void createAndSaveTransaction(CreateTransactionDto createTransactionDto);

    ViewTransactionHistoryResponse viewTransactionHistory(ViewTransactionHistoryRequest viewTransactionHistoryRequest);
}
