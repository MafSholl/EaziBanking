package com.nibss.eazibank.dto.response;

import lombok.*;

@Data
@RequiredArgsConstructor
//@AllArgsConstructor
public class AccountBalanceRequest {

    @NonNull
    private String accountNumber;

}
