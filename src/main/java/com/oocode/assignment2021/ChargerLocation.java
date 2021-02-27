package com.oocode.assignment2021;

import java.util.List;

public class ChargerLocation {
    public final String name;
    public final int distance;
    public final List<Charger> chargers;

    public ChargerLocation(String name, int distance, List<Charger> chargers) {
        this.name = name;
        this.distance = distance;
        this.chargers = chargers;
    }
}
