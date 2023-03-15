package com.eazibank.remabank.bank.models;

import com.eazibank.remabank.data.models.enums.DirectorRole;
import lombok.Data;

import java.math.BigInteger;
@Data
public class Director {
    private String firstName;
    private String lastName;
    private DirectorRole role;
    private int votingPower;
    private BigInteger compensation;
}
