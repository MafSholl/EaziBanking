package com.nibss.eazibank.nibss.controller.models;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
public class LogHistory {

    private String transactionRefenceId;
    private String userBank;
    private String userFirstName;
    private String userLastName;
    private List<AtmQuery> queries = new ArrayList<>();
    private LocalDateTime dateAndDate;
}