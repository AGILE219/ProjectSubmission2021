package com.oocode.assignment2021;

import org.junit.Test;

import java.io.IOException;

public class ManualTest {
    @Test
    public void test() throws IOException {
        System.out.println(ChargerFinder.findBestCharger(
                "https://tranquil-headland-85251.herokuapp.com",
                64,
                "CCS",
                "Durham",
                23));
    }
}
