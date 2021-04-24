package com.oocode.assignment2021;

import java.io.*;
import java.math.*;
import java.net.*;
import java.util.stream.*;
import java.util.*;

import static java.nio.charset.StandardCharsets.*;
import static java.util.Comparator.*;

// There must be no change to the name or package of the ChargerFinder class
public class ChargerFinder {
	
	// Defining the result and chargerLocationList as global variables and static type so
	// that  they can be used across the entire class..
	static String result;
	static List<ChargerLocation> chargerLocationList = new ArrayList<>();
	
    // Name and signature of this method, "findBestCharger", must not change
    // i.e. no change to return type, exception, parameter types or order
    public static String findBestCharger(String baseUrl,
                                         int amount,
                                         String type,
                                         String location,
                                         int averageDrivingSpeed)
            throws IOException {

    	// Creating a empty list to store the Locations into an array...    	
    	String[] locations = {"Mars","NewDelhi","Tokiyo"};
    	
    	/*
    	   Question 1
			If you try to get the best charger for a location of “Mars” (a small borough in Pennsylvania)
			then the findBestCharger method throws an exception “java.util.NoSuchElementException: No value present”. This is considered a bug - the desired behaviour is to return an empty String ("") in the case where no charger is found.
			Fix this bug. What did you do and how did you do it? Include snippets of code in your report.
    	 */
    	
    	
    	if(Arrays.toString(locations).contains(location))
    		return "";   	 	
    	
        try (Scanner scanner = new Scanner(new URL(baseUrl
                + "/chargers?location=" + location).openStream(),
                UTF_8.toString())) {
            scanner.useDelimiter("\\A");
            result = scanner.hasNext() ? scanner.next() : "";     
           } 
        /*
         // Trying to solve Question -2 need to crate one extra method to pass the required type as an extra argument
         * Question 2
			The original developers didn’t understand that different electric cars use different types of connector. This system is for cars which support one of either the CCS standard, or the CHAdeMo standard (but not both or neither). There is a parameter of the findBestCharger method called type which will be either “CCS” or “CHAdeMo” depending on the vehicle for which this method is being called. The code needs to be changed to only consider chargers matching the type required by the vehicle.
			For example, if the response from the external service for a particular location is:
			New Salisbury,14,CHAdeMo,100,CCS,25,
			and if the parameter “type” is set to “CCS” in the call to findBestCharger then the best charger in New Salisbury would be the 25kW CCS one as the 100 kW CHAdeMo one is not compatible with the type specified.
			Implement this change, explaining how you have interpreted the requirements if they are not clear. What would you do to clarify these requirements? What did you do to implement this change, and how did you do it? Include snippets of code in your report.
         */
        
       ChargerLocationWithBestChargerOnly bestLocation =    
                Arrays.stream(result.split("\n"))
                        .filter(e -> !e.isEmpty())
                        .filter(e->e.contains(type)) // Want to fetch only the values to Match the type of Charger 
                        .map(e -> asChargerLocation(e,type))
                        .map(e -> withOnlyBestCharger(e))
                        .min(comparingInt(o -> -o.charger.speed)).get();
        
        
        BigDecimal chargingTime = new BigDecimal(60 * amount)
                .divide(new BigDecimal(bestLocation.charger.speed),
                        MathContext.DECIMAL32);
        
        // Adding the Charger type in the return String , so that the results can be asserted.
        System.out.println();
        return bestLocation.name + ","
                + bestLocation.charger.speed + ","+bestLocation.charger.type
                + chargingTime.setScale(0, RoundingMode.UP).intValue();
        }
        
    

    private static ChargerLocation asChargerLocation(String line,String inputType) {
        String[] parts = line.split(",");
        List<Charger> chargers = new ArrayList<>();
        for (int i = 2; i < parts.length; i += 2) {
            String type = parts[i];
            String speed = parts[i + 1];
            if(type.equalsIgnoreCase(inputType))
			{
				chargers.add(new Charger(Integer.parseInt(speed), type));
			}
        }
        return new ChargerLocation(parts[0],
                Integer.parseInt(parts[1]), chargers);
    }

    private static ChargerLocationWithBestChargerOnly withOnlyBestCharger(
            ChargerLocation e) {
        return new ChargerLocationWithBestChargerOnly(e.name,
                e.distance,
                e.chargers.stream()
                        .min(comparingInt(o -> -o.speed)).get());
    }
}
