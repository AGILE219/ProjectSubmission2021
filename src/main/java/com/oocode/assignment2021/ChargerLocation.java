package com.oocode.assignment2021;

import java.util.*;

public class ChargerLocation {
    public final String name;
    public final int distance;
    public final int amount;
    public final int avgSpeed;
    public final int chargingSpeed;
    public final List<Charger> chargers;
   

    public ChargerLocation(String name, int distance, int amount, int avgSpeed ,int chargingSpeed,List<Charger> chargers) {
        this.name = name;
        this.distance = distance;
        this.amount = amount;
        this.avgSpeed = avgSpeed;
        this.chargers = chargers;
        this.chargingSpeed = chargingSpeed;
    }
    

    
}
