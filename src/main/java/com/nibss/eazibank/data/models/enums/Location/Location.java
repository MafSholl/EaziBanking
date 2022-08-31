package com.nibss.eazibank.data.models.enums.Location;

public enum Location {

    LAGOS(),
    OYO(),
    ABUJA();
    
    private Lagos[] cities;
    Location(Lagos... cities){
        this.cities = cities;
    }
}
