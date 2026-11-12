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
        System.out.println("==================================================");
        System.out.println("   RUNNING ROBUSTNESS TESTS ACROSS MULTIPLE PROFILES");
        System.out.println("==================================================\n");

        ClassifierContext context = new ClassifierContext();

        // --- Test 1: Edge-Ring Profile ---
        Wafer edgeRingWafer = WaferParser.parseWaferData("WAFER-EDGERING-01", 20, "EDGERING");
        context.setStrategy(new EdgeRingAndScratchStrategy());
        ConcurrentWaferAnalyzer.analyzeConcurrently(edgeRingWafer, new EdgeRingAndScratchStrategy());
        WaferReportGenerator.printExecutiveSummary(edgeRingWafer);

        // --- Test 2: Scratch Profile ---
        Wafer scratchWafer = WaferParser.parseWaferData("WAFER-SCRATCH-02", 20, "SCRATCH");
        context.setStrategy(new EdgeRingAndScratchStrategy());
        ConcurrentWaferAnalyzer.analyzeConcurrently(scratchWafer, new EdgeRingAndScratchStrategy());
        WaferReportGenerator.printExecutiveSummary(scratchWafer);

        // --- Test 3: Center-Cluster Profile ---
        Wafer clusterWafer = WaferParser.parseWaferData("WAFER-CLUSTER-03", 20, "CENTERCLUSTER");
        context.setStrategy(new CenterClusterStrategy());
        ConcurrentWaferAnalyzer.analyzeConcurrently(clusterWafer, new CenterClusterStrategy());
        WaferReportGenerator.printExecutiveSummary(clusterWafer);
    }
}
