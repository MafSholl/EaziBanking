package com.eazibank.egobank.staff.models;

public enum SalaryLevel {
    BEGINNER(Level.I, Level.II, Level.III),
    MIDDLE(Level.I, Level.II, Level.III),
    SENIOR(Level.I, Level.II, Level.III);
    private Level[] salaryLevels;

    SalaryLevel(Level... salaryLevels) {
        this.salaryLevels = salaryLevels;
    }
}
