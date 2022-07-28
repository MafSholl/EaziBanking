package com.nibss.eazibank.dto.request;

import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;

@Data
public class CustomerDepositRequest {

    @NonNull
    private String accountNumber;
    @NonNull
    private BigInteger amount;
    private String narration;

}
