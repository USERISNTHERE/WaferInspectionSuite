package com.wafer.model;

public abstract class GridNode {
    protected int gridX;
    protected int gridY;

    public GridNode(int gridX, int gridY) {
        this.gridX = gridX;
        this.gridY = gridY;
    }

    public int getGridX() { return gridX; }
    public int getGridY() { return gridY; }

    // Abstract method to be overridden by subclasses for custom status reporting
    public abstract String getNodeStatus();
}
