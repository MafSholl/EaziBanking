package com.nibss.eazibank.account.dto.request;

import lombok.*;

@Data
@RequiredArgsConstructor
//@AllArgsConstructor
public class AccountBalanceRequest {

    @NonNull
    private String accountNumber;

}
