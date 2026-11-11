package com.wafer.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Wafer {
    private String waferId;
    private int radius;
    // Composition: A Wafer is composed of thousands of Die objects mapped by their coordinates
    private Map<String, Die> dieGrid;

    public Wafer(String waferId, int radius) {
        this.waferId = waferId;
        this.radius = radius;
        this.dieGrid = new HashMap<>();
    }

    // Add or update a die in the wafer matrix
    public void addDie(Die die) {
        String key = die.getXCoord() + "," + die.getYCoord();
        dieGrid.put(key, die);
    }

    public Die getDie(int x, int y) {
        return dieGrid.get(x + "," + y);
    }

    public Collection<Die> getAllDies() {
        return dieGrid.values();
    }

    // Calculate yield percentage statistics
    public double calculateYieldPercentage() {
        if (dieGrid.isEmpty()) return 0.0;
        
        long defectiveCount = dieGrid.values().stream()
                .filter(Die::isDefective)
                .count();
        
        long totalDies = dieGrid.size();
        return ((double) (totalDies - defectiveCount) / totalDies) * 100.0;
    }

    public int getTotalDies() {
        return dieGrid.size();
    }

    public long getDefectiveCount() {
        return dieGrid.values().stream().filter(Die::isDefective).count();
    }

    public String getWaferId() { return waferId; }
}
