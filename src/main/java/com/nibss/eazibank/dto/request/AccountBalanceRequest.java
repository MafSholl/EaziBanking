package com.nibss.eazibank.dto.request;

import lombok.*;

@Data
@RequiredArgsConstructor
//@AllArgsConstructor
public class AccountBalanceRequest {

    @NonNull
    private String accountNumber;

}
