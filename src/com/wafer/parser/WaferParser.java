package com.wafer.parser;

import com.wafer.model.Die;
import com.wafer.model.Wafer;

import java.util.Random;

public class WaferParser {

    // Overloaded method supporting specific profile types for testing robustness
    public static Wafer parseWaferData(String waferId, int gridSize, String profile) {
        Wafer wafer = new Wafer(waferId, gridSize / 2);
        Random random = new Random();

        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                double voltageLeakage = 0.5 + (2.5 - 0.5) * random.nextDouble();
                double switchingSpeed = 10.0 + (50.0 - 10.0) * random.nextDouble();

                Die die = new Die(x, y, voltageLeakage, switchingSpeed);

                // Apply targeted defect logic based on the requested manufacturing profile
                if ("EDGERING".equalsIgnoreCase(profile)) {
                    int threshold = 3;
                    if (x <= threshold || x >= gridSize - threshold || y <= threshold || y >= gridSize - threshold) {
                        die.setDefective(true);
                        die.setVoltageLeakage(2.8); // High leakage signature
                    }
                } else if ("SCRATCH".equalsIgnoreCase(profile)) {
                    if (Math.abs(x - y) <= 1) {
                        die.setDefective(true);
                        die.setSwitchingSpeed(11.0); // Slow switching signature
                    }
                } else if ("CENTERCLUSTER".equalsIgnoreCase(profile)) {
                    int center = gridSize / 2;
                    double dist = Math.sqrt(Math.pow(x - center, 2) + Math.pow(y - center, 2));
                    if (dist <= 4.0) {
                        die.setDefective(true);
                        die.setVoltageLeakage(2.9);
                    }
                } else {
                    // Default random injection
                    if (voltageLeakage > 2.2 || switchingSpeed < 15.0) {
                        die.setDefective(true);
                    }
                }

                wafer.addDie(die);
            }
        }
        return wafer;
    }
}
