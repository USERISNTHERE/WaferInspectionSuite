package com.wafer.strategy;

import com.wafer.model.Wafer;

public interface DefectClassifierStrategy {
    // Every strategy must implement this analyze method
    void analyze(Wafer wafer);
}
