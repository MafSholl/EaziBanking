package com.nibss.eazibank.bank.models;

import lombok.Data;

import java.math.BigInteger;
@Data
public class Shareholder {
    private String firstName;
    private String lastName;
    private long shareVolume;
    private BigInteger sharePrice;
    private BigInteger shareWorth;
}
