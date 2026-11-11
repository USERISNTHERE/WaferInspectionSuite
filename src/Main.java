package com.wafer;

import com.wafer.model.Wafer;
import com.wafer.parser.WaferParser;
import com.wafer.strategy.ClassifierContext;
import com.wafer.strategy.CenterClusterStrategy;
import com.wafer.strategy.EdgeRingAndScratchStrategy;

public class Main {
    public static void main(String[] args) {
        System.out.println("Initializing Wafer Inspection Suite...");

        // 1. Parse/Generate a sample wafer data batch (Grid size: 20x20)
        Wafer testWafer = WaferParser.parseWaferData("WAFER-2026-A", 20);
        System.out.println("Wafer loaded successfully: " + testWafer.getWaferId());

        // 2. Setup Strategy Context
        ClassifierContext context = new ClassifierContext();

        // 3. Apply Edge-Ring and Scratch Strategy
        System.out.println("Running Edge-Ring & Scratch Detection Strategy...");
        context.setStrategy(new EdgeRingAndScratchStrategy());
        context.executeAnalysis(testWafer);

        // 4. Apply Center-Cluster Strategy
        System.out.println("Running Center-Cluster Yield Strategy...");
        context.setStrategy(new CenterClusterStrategy());
        context.executeAnalysis(testWafer);

        // 5. Output summary results
        long defectiveCount = testWafer.getAllDies().stream().filter(d -> d.isDefective()).count();
        System.out.println("Analysis Complete!");
        System.out.println("Total Dies Inspected: " + testWafer.getAllDies().size());
        System.out.println("Total Defective Dies Flagged: " + defectiveCount);
    }
}
