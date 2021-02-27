package com.oocode.assignment2021;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

public class RestClient {
    public String get(String urlString) throws IOException {
        try (Scanner scanner = new Scanner(new URL(urlString).openStream(), UTF_8.toString())) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
    }
}
