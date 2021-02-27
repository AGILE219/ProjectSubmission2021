package com.oocode.assignment2021;

import java.io.*;
import java.math.*;
import java.net.*;
import java.util.*;

import static java.nio.charset.StandardCharsets.*;
import static java.util.Comparator.*;

// There must be no change to the name or package of the ChargerFinder class
public class ChargerFinder {
    // Name and signature of this method, "findBestCharger", must not change
    // i.e. no change to return type, exception, parameter types or order
    public static String findBestCharger(String baseUrl,
                                         int amount,
                                         String type,
                                         String location,
                                         int averageDrivingSpeed)
            throws IOException {
        String result;
        try (Scanner scanner = new Scanner(new URL(baseUrl
                + "/chargers?location=" + location).openStream(),
                UTF_8.toString())) {
            scanner.useDelimiter("\\A");
            result = scanner.hasNext() ? scanner.next() : "";
        }
        ChargerLocationWithBestChargerOnly bestLocation =
                Arrays.stream(result.split("\n"))
                        .filter(e -> !e.isBlank())
                        .map(e -> asChargerLocation(e))
                        .map(e -> withOnlyBestCharger(e))
                        .min(comparingInt(o -> -o.charger.speed)).get();

        BigDecimal chargingTime = new BigDecimal(60 * amount)
                .divide(new BigDecimal(bestLocation.charger.speed),
                        MathContext.DECIMAL32);
        return bestLocation.name + ","
                + bestLocation.charger.speed + ","
                + chargingTime.setScale(0, RoundingMode.UP).intValue();
    }

    private static ChargerLocation asChargerLocation(String line) {
        String[] parts = line.split(",");
        List<Charger> chargers = new ArrayList<>();
        for (int i = 2; i < parts.length; i += 2) {
            String type = parts[i];
            String speed = parts[i + 1];
            chargers.add(new Charger(Integer.parseInt(speed), type));
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
