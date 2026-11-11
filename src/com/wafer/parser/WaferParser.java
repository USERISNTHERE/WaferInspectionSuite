package com.wafer.parser;

import com.wafer.model.Die;
import com.wafer.model.Wafer;

import java.util.Random;

public class WaferParser {

    // Simulates parsing a wafer file or dataset and returning a populated Wafer object
    public static Wafer parseWaferData(String waferId, int gridSize) {
        Wafer wafer = new Wafer(waferId, gridSize / 2);
        Random random = new Random();

        // Generate a grid of dies
        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                // Simulate electrical parameters
                double voltageLeakage = 0.5 + (2.5 - 0.5) * random.nextDouble();
                double switchingSpeed = 10.0 + (50.0 - 10.0) * random.nextDouble();

                Die die = new Die(x, y, voltageLeakage, switchingSpeed);
                
                // Randomly inject some initial failures for testing strategies
                if (voltageLeakage > 2.2 || switchingSpeed < 15.0) {
                    die.setDefective(true);
                }

                wafer.addDie(die);
            }
        }
        return wafer;
    }
}
