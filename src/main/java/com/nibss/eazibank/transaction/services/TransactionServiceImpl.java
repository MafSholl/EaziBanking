package com.nibss.eazibank.transaction.services;

import com.nibss.eazibank.data.models.enums.TransactionType;
import com.nibss.eazibank.data.repositories.TransactionRepository;
import com.nibss.eazibank.dto.CreateTransactionDto;
import com.nibss.eazibank.dto.request.ViewTransactionHistoryRequest;
import com.nibss.eazibank.dto.response.ViewTransactionHistoryResponse;
import com.nibss.eazibank.transaction.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public void createAndSaveTransaction(CreateTransactionDto createTransactionDto) {
        Transaction transaction = Transaction.builder()
                .accountNumber(createTransactionDto.getAccountNumber())
                .amount(createTransactionDto.getAmount())
                .description(createTransactionDto.getDescription())
                .transactionType(TransactionType.DEPOSIT)
                .recipientAccountNumber(createTransactionDto.getAccountNumber())
                .recipientName("Self")
                .transactionId("002")
                .build();
        transactionRepository.save(transaction);
    }

    @Override
    public ViewTransactionHistoryResponse viewTransactionHistory(ViewTransactionHistoryRequest viewTransactionHistoryRequest) {
        List<Transaction> transactionHistory2 = transactionRepository.findByAccountNumber(viewTransactionHistoryRequest.getAccountNumber());
        ViewTransactionHistoryResponse transactionHistoryResponse = new ViewTransactionHistoryResponse();
        transactionHistoryResponse.setTransactionHistory(transactionHistory2);
        return transactionHistoryResponse;
    }
}
