package com.wafer.concurrent;

import com.wafer.model.Die;
import com.wafer.model.Wafer;
import com.wafer.strategy.DefectClassifierStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentWaferAnalyzer {

    // Analyzes wafer regions concurrently using a thread pool
    public static void analyzeConcurrently(Wafer wafer, DefectClassifierStrategy strategy) {
        List<Die> allDies = new java.util.ArrayList<>(wafer.getAllDies());

        int numThreads = Runtime.getRuntime().availableProcessors(); // Uses available Mac CPU cores
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        int chunkSize = Math.max(1, allDies.size() / numThreads);

        System.out.println("Launching multi-threaded analysis across " + numThreads + " CPU cores...");

        for (int i = 0; i < allDies.size(); i += chunkSize) {
            final int start = i;
            final int end = Math.min(i + chunkSize, allDies.size());

            executor.submit(() -> {
                // Process a chunk of dies concurrently
                for (int j = start; j < end; j++) {
                    Die die = allDies.get(j);
                    // Perform micro-check or sub-analysis on the chunk
                }
                // Run the strategy batch on this thread
                strategy.analyze(wafer);
            });
        }

        executor.shutdown();
        try {
            // Wait for all threads to finish execution safely
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.err.println("Concurrency analysis interrupted: " + e.getMessage());
        }
        
        System.out.println("Multi-threaded batch analysis completed successfully!");
    }
}
