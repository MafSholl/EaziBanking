package com.eazibank.remabank.transaction.services;

import com.eazibank.remabank.transaction.dto.CreateTransactionDto;
import com.eazibank.remabank.transaction.dto.request.ViewTransactionHistoryRequest;
import com.eazibank.remabank.transaction.dto.response.ViewTransactionHistoryResponse;
import com.eazibank.remabank.transaction.models.Transaction;
import com.eazibank.remabank.transaction.repository.TransactionRepository;
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
//                .transactionType(TransactionType.DEPOSIT)
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
