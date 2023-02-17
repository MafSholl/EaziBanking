package com.nibss.eazibank.nibss.controller.models.enums;

import static com.nibss.eazibank.data.models.enums.Level.*;

public enum SalaryLevel {
    BEGINNER(I, II, III),
    MIDDLE(I, II, III),
    SENIOR(I, II, III);
    private Level[] salaryLevels;

    SalaryLevel(Level... salaryLevels) {
        this.salaryLevels = salaryLevels;
    }
}
