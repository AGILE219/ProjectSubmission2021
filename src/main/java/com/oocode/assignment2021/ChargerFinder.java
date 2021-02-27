package com.oocode.assignment2021;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// There must be no change to the name or package of the ChargerFinder class
public class ChargerFinder {
    // The name and signature of this one method, "findBestCharger", must not change; i.e. no change to return type, exception declared thrown, parameter types or their order
    public static String findBestCharger(String baseUrl, int amount, String type, String location, int averageDrivingSpeed) throws IOException {
        RestClient restClient = new RestClient();
        String text = restClient.get(baseUrl + "/chargers?location=" + location);
        ChargerLocationWithBestChargerOnly bestLocation = Arrays.stream(text.split("\n"))
                .filter(e -> !e.isBlank())
                .map(e -> asChargerLocation(e))
                .map(e -> withOnlyBestCharger(e))
                .min(Comparator.comparingInt(o -> -o.charger.speed)).get();

        BigDecimal chargingTime = new BigDecimal(60 * amount).divide(new BigDecimal(bestLocation.charger.speed), MathContext.DECIMAL32);
        return bestLocation.name + "," + bestLocation.charger.speed + "," + chargingTime.setScale(0, RoundingMode.UP).intValue();
    }

    private static ChargerLocation asChargerLocation(String line) {
        String[] parts = line.split(",");
        List<Charger> chargers = new ArrayList<>();
        for (int i = 2; i < parts.length; i += 2) {
            String type = parts[i];
            String speed = parts[i + 1];
            chargers.add(new Charger(Integer.parseInt(speed), type));
        }
        return new ChargerLocation(parts[0], Integer.parseInt(parts[1]), chargers);
    }

    private static ChargerLocationWithBestChargerOnly withOnlyBestCharger(ChargerLocation e) {
        return new ChargerLocationWithBestChargerOnly(e.name, e.distance, e.chargers.stream().min(Comparator.comparingInt(o -> -o.speed)).get());
    }
}
