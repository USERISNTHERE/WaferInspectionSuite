package com.wafer;

import com.wafer.model.Wafer;
import com.wafer.parser.WaferParser;
import com.wafer.strategy.ClassifierContext;
import com.wafer.strategy.EdgeRingAndScratchStrategy;
import com.wafer.concurrent.ConcurrentWaferAnalyzer;

public class Main {
    public static void main(String[] args) {
        System.out.println("Initializing Wafer Inspection Suite (Phase 4)...");

        // 1. Parse/Generate sample wafer data (Grid size: 30x30 for heavier load)
        Wafer testWafer = WaferParser.parseWaferData("WAFER-2026-MULTI", 30);
        System.out.println("Wafer loaded: " + testWafer.getWaferId() + " with " + testWafer.getAllDies().size() + " dies.");

        // 2. Run Multi-Threaded Analysis
        ClassifierContext context = new ClassifierContext();
        context.setStrategy(new EdgeRingAndScratchStrategy());

        long startTime = System.currentTimeMillis();
        
        // Execute concurrently
        ConcurrentWaferAnalyzer.analyzeConcurrently(testWafer, new EdgeRingAndScratchStrategy());

        long endTime = System.currentTimeMillis();
        System.out.println("Concurrency Execution Time: " + (endTime - startTime) + " ms");

        // 3. Summary results
        long defectiveCount = testWafer.getAllDies().stream().filter(Die::isDefective).count();
        System.out.println("Total Defective Dies Flagged: " + defectiveCount);
    }
}
