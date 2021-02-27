package com.oocode.assignment2021;

import org.junit.Test;

import java.io.IOException;

public class ManualTest {
    @Test
    public void test() throws IOException {
        // curl https://tranquil-headland-85251.herokuapp.com/chargers?location=Oxford
        /*
Find Best Charger for adding 64 kWh of charge to a vehicle compatible with "CCS" chargers, near Oxford,
given average speed (for travelling to charger) is 25 mph.

Might produce something like:

East London,25,154

meaning, best charger is at East London, has a charging speed of 25kW and will take 154 minutes to charge
         */
        System.out.println(ChargerFinder.findBestCharger("https://tranquil-headland-85251.herokuapp.com", 64, "CCS", "Oxford", 25));
    }
}
