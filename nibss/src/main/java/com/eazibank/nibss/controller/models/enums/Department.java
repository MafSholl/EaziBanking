package com.eazibank.nibss.controller.models.enums;

import java.util.Arrays;
import java.util.List;

public enum Department {

    INVESTMENT, HUMAN_RESOURCES,
    OPERATIONS,
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
