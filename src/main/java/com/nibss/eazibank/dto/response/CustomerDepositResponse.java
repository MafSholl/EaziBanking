package com.nibss.eazibank.dto.response;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
@Data
public class CustomerDepositResponse {

    private String firstName;
    private String lastName;
    private BigInteger amount;
    private boolean isSuccessful;
}
