package com.wafer.strategy;

import com.wafer.model.Die;
import com.wafer.model.Wafer;

public class EdgeRingAndScratchStrategy implements DefectClassifierStrategy {

    @Override
    public void analyze(Wafer wafer) {
        int threshold = 3; // Simulating border threshold for edge-rings

        for (Die die : wafer.getAllDies()) {
            // Check if die is on the outer boundary (Edge-Ring pattern)
            if (die.getXCoord() <= threshold || die.getXCoord() >= 20 - threshold ||
                die.getYCoord() <= threshold || die.getYCoord() >= 20 - threshold) {
                
                if (die.isDefective()) {
                    die.setDefectCategory("Edge-Ring Defect");
                }
            } 
            // Check for linear scratch patterns (diagonal logic simulation)
            else if (Math.abs(die.getXCoord() - die.getYCoord()) <= 1 && die.isDefective()) {
                die.setDefectCategory("Scratch Defect");
            }
        }
    }
}

