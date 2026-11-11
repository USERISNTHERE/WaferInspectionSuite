package com.wafer.model;

public class Die {
    private int xCoord;
    private int yCoord;
    private double voltageLeakage;
    private double switchingSpeed;
    private boolean isDefective;
    private String defectCategory; // e.g., "Edge-Ring", "Center", "Scratch", "Normal"

    // Constructor
    public Die(int xCoord, int yCoord, double voltageLeakage, double switchingSpeed) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.voltageLeakage = voltageLeakage;
        this.switchingSpeed = switchingSpeed;
        this.isDefective = false;
        this.defectCategory = "Normal";
    }

    // Getters and Setters (Encapsulation)
    public int getXCoord() { return xCoord; }
    public int getYCoord() { return yCoord; }
    
    public double getVoltageLeakage() { return voltageLeakage; }
    public void setVoltageLeakage(double voltageLeakage) { this.voltageLeakage = voltageLeakage; }

    public double getSwitchingSpeed() { return switchingSpeed; }
    public void setSwitchingSpeed(double switchingSpeed) { this.switchingSpeed = switchingSpeed; }

    public boolean isDefective() { return isDefective; }
    public void setDefective(boolean defective) { isDefective = defective; }

    public String getDefectCategory() { return defectCategory; }
    public void setDefectCategory(String defectCategory) { this.defectCategory = defectCategory; }


    @Override
    public String toString() {
        return String.format("Die[%d,%d] (Leakage: %.2f, Speed: %.2f) -> %s", 
                xCoord, yCoord, voltageLeakage, switchingSpeed, defectCategory);
    }
}
