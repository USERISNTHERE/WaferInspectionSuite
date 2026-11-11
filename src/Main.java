package com.wafer;

import com.wafer.model.Wafer;
import com.wafer.parser.WaferParser;
import com.wafer.strategy.ClassifierContext;
import com.wafer.strategy.EdgeRingAndScratchStrategy;
import com.wafer.strategy.CenterClusterStrategy;
import com.wafer.concurrent.ConcurrentWaferAnalyzer;
import com.wafer.report.WaferReportGenerator;

public class Main {
    public static void main(String[] args) {
        System.out.println("Initializing Wafer Inspection Suite (Final Pipeline)...");

        // 1. Ingest/Parse Wafer Dataset
        Wafer testWafer = WaferParser.parseWaferData("WAFER-2026-FINAL", 30);

        // 2. Execute Multi-Threaded Defect Classification Strategies
        ClassifierContext context = new ClassifierContext();
        
        context.setStrategy(new EdgeRingAndScratchStrategy());
        ConcurrentWaferAnalyzer.analyzeConcurrently(testWafer, new EdgeRingAndScratchStrategy());

        context.setStrategy(new CenterClusterStrategy());
        ConcurrentWaferAnalyzer.analyzeConcurrently(testWafer, new CenterClusterStrategy());

        // 3. Generate Final Executive Report Dashboard
        WaferReportGenerator.printExecutiveSummary(testWafer);
    }
}
