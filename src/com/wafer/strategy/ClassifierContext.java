package com.wafer.strategy;

import com.wafer.model.Wafer;

public class ClassifierContext {
    private DefectClassifierStrategy strategy;

    // Allow strategy to be set dynamically at runtime (Strategy Pattern)
    public void setStrategy(DefectClassifierStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeAnalysis(Wafer wafer) {
        if (strategy == null) {
            throw new IllegalStateException("Defect classifier strategy is not set!");
        }
        strategy.analyze(wafer);
    }
}
