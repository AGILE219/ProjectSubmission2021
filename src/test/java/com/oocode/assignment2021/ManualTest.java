package com.oocode.assignment2021;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

public class ManualTest {
    @Test
    public void test() throws IOException {
    	System.out.println(ChargerFinder.findBestCharger("https://tranquil-headland-85251.herokuapp.com", 64, "CCS", "Durham", 23));
    }
    
    
    @Test
    public void testMarsLocation() throws IOException {
    	String mars = ChargerFinder.findBestCharger("https://tranquil-headland-85251.herokuapp.com", 64, "CCS", "Mars", 23);
    	
    	// Asserting for an empty String when the location is Mars
    	assertTrue(mars.isEmpty());
    	
    }
}
