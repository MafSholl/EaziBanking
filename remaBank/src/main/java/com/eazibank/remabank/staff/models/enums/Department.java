package com.eazibank.remabank.staff.models.enums;

import java.util.Arrays;
import java.util.List;

import static com.eazibank.remabank.staff.models.enums.SubDepartment.*;

public enum Department {

    INVESTMENT, HUMAN_RESOURCES,
    OPERATIONS(CUSTOMER_SERVICE, CASH_AND_TELLER, FUNDS_TRANSFER, MARKETING),
    SECRETARY,
    DISPATCH,
    SANITARY;

    private SubDepartment[] subDepartments;

    Department(SubDepartment... subDepartments) {
        this.subDepartments = subDepartments;
    }

    public static SubDepartment getSubDepartment(String subDepartment) {
        if(Arrays.toString(OPERATIONS.subDepartments).contains(subDepartment.toUpperCase())) {
            SubDepartment[] array = OPERATIONS.subDepartments;
            for(SubDepartment element : array) {
                if (element.toString().equalsIgnoreCase(subDepartment)) return element;
            }
        }

        List<SubDepartment> subdepartment = Arrays.stream(OPERATIONS.subDepartments).toList();
        return null;
    }
}
