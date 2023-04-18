package com.eazibank.nibss.controller.models.enums.Location;

public enum Location {

    LAGOS(),
    OYO(),
    ABUJA();
    
    private Lagos[] cities;
    Location(Lagos... cities){
        this.cities = cities;
    }
}
