package com.nibss.eazibank.customer.dto.response;

import com.nibss.eazibank.data.models.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewProfileResponse {

    private String BVN;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private AccountType accountType;
    private LocalDateTime DOB;
}