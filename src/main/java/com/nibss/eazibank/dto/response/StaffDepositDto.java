package com.nibss.eazibank.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StaffDepositDto {
    private String firstName;
    private String lastName;
    private String accountNumber;
    private String amount;
    private boolean success;
    private String staffId;
}
