package com.wafer.strategy;

import com.wafer.model.Die;
import com.wafer.model.Wafer;

public class CenterClusterStrategy implements DefectClassifierStrategy {

    @Override
    public void analyze(Wafer wafer) {
        int centerCoord = 10; // Assuming a 20x20 grid center
        int radiusRange = 4;

        for (Die die : wafer.getAllDies()) {
            double distanceFromCenter = Math.sqrt(
                Math.pow(die.getXCoord() - centerCoord, 2) + 
                Math.pow(die.getYCoord() - centerCoord, 2)
            );

            // If it's near the center and marked defective
            if (distanceFromCenter <= radiusRange && die.isDefective()) {
                die.setDefectCategory("Center-Cluster Defect");
            }
        }
    }
}
