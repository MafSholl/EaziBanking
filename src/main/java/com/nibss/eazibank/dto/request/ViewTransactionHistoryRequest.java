package com.nibss.eazibank.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewTransactionHistoryRequest {

    private String email;
    private String password;
    private String accountNumber;
}
