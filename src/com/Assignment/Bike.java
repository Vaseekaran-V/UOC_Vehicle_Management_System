package com.Assignment;

public class Bike extends Vehicle {
    private String type; //whether it is a scooter, racing bike, or general
    private boolean helmet;
    private boolean jacket;

    public Bike(String plate, String make, String model, double ratePerDay, String type, boolean helmet, boolean jacket) {
        super(plate, make, model, ratePerDay);
        this.type = type;
        this.helmet = helmet;
        this.jacket = jacket;
    }

    public String getType() {
        return type;
    }

    public boolean isHelmet() {
        return helmet;
    }

    public boolean isJacket() {
        return jacket;
    }

    public void setHelmet(boolean helmet) {
        this.helmet = helmet;
    }

    public void setJacket(boolean jacket) {
        this.jacket = jacket;
    }
}
