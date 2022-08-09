package com.nibss.eazibank.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class CustomerDepositRequest {

    @NonNull
    private String accountNumber;
    private String firstName;
    private String lastName;
    @NonNull
    private BigInteger amount;
    private String narration;
}
