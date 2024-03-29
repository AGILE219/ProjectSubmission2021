package com.oocode.assignment2021;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

public class ManualTest {
   
	// Default test case to check the system is working as expected and showing the expected result
	@Test
    public void test() throws IOException {
    	System.out.println(ChargerFinder.findBestCharger("https://tranquil-headland-85251.herokuapp.com", 64, "CCS", "Durham", 23));
    	
    	System.out.println("=======Test case completed============");
	}
	
	
   
    
	//Question -1 , This test case is to check for the invalid location, which is not supported by the application. 
   
    @Test
    public void testMarsLocation() throws IOException {
    	
    	String mars = ChargerFinder.findBestCharger("https://tranquil-headland-85251.herokuapp.com", 64, "CCS", "Mars", 23);
    	// Asserting for an empty String when the location is Mars
    	assertTrue(mars.isEmpty());    	
    	
    	String Tokiyo = ChargerFinder.findBestCharger("https://tranquil-headland-85251.herokuapp.com", 64, "CCS", "Tokiyo", 23);
    	// Asserting for an empty String when the location is Tokiyo
    	assertTrue(Tokiyo.isEmpty());
    	
    	System.out.println("=======Test case completed============");
    	  	
    }

    // Question -2, This test case is to check the result should only return the location which has the required Type Of the charger ...   	
    	
    @Test
    public void testForParticularType() throws IOException {
    	
    	System.out.println(ChargerFinder.findBestCharger("https://tranquil-headland-85251.herokuapp.com", 64, "CHAdeMo", "Durham", 23));
    	System.out.println(ChargerFinder.findBestCharger("https://tranquil-headland-85251.herokuapp.com", 64, "CCS", "NewSalisbury", 23));;

    	System.out.println("=======Test case completed============");
    }
    
    // Question -3 , Considering the time required to reach out to the Nearest charging point
	// Fetching the minimum of  amount/charingSpeed + distanceToCharger/avgSpeed 
    
    @Test
    public void testForTheShortestDistance() throws IOException {
    	System.out.println(ChargerFinder.findBestCharger("https://tranquil-headland-85251.herokuapp.com", 64, "CCS", "Durham", 23));
    	
    	System.out.println("=======Test case completed============");
    	
    }
    
    
}
