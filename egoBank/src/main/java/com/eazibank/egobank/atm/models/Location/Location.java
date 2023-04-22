package com.eazibank.egobank.atm.models.Location;

public enum Location {

    LAGOS(),
    OYO(),
    ABUJA();
    
    private Lagos[] cities;
    Location(Lagos... cities){
        this.cities = cities;
    }
}
