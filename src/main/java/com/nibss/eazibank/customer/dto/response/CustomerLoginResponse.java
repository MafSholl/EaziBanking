package com.nibss.eazibank.customer.dto.response;

import com.nibss.eazibank.account.models.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerLoginResponse {

    private String firstName;
    private String lastName;
    private String BVN;
    private Map<String, Account> customerAccounts;
    private boolean success;
}