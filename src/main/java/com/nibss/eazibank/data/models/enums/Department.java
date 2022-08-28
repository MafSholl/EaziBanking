package com.nibss.eazibank.data.models.enums;

import java.util.Arrays;

import static com.nibss.eazibank.data.models.enums.SubDepartment.*;

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
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getSubDepartment("Customer_service"));
    }
}