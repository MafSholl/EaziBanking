package com.eazibank.remabank.staff.models.enums;

import com.eazibank.remabank.staff.models.enums.Level;

import static com.eazibank.remabank.staff.models.enums.Level.*;

public enum SalaryLevel {
    BEGINNER(I, II, III),
    MIDDLE(I, II, III),
    SENIOR(I, II, III);
    private Level[] salaryLevels;

    SalaryLevel(Level... salaryLevels) {
        this.salaryLevels = salaryLevels;
    }
}
