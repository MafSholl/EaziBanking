package com.nibss.eazibank.data.models.enums;

public enum SalaryLevel {
    BEGINNER(I, II, III),
    MIDDLE(I, II, III),
    SENIOR(I, II, III);
    private Level[] salaryLevels;

    SalaryLevel(Level... salaryLevels) {
        this.salaryLevels = salaryLevels;
    }
}
