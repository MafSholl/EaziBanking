package com.eazibank.remabank.nibss.controller.models.enums;

public enum SalaryLevel {
    BEGINNER,
    MIDDLE,
    SENIOR;
    private Level[] salaryLevels;

    SalaryLevel(Level... salaryLevels) {
        this.salaryLevels = salaryLevels;
    }
}
