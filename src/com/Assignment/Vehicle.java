package com.Assignment;

public abstract class Vehicle {
    private String plate;
    private String make;
    private String model;
    private double ratePerDay;

    public Vehicle(String plate, String make, String model, double ratePerDay) {
        this.plate = plate;
        this.make = make;
        this.model = model;
        this.ratePerDay = ratePerDay;
    }

    public String getPlate() {
        return plate;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public double getRatePerDay() {
        return ratePerDay;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public void setRatePerDay(double ratePerDay) {
        this.ratePerDay = ratePerDay;
    }
}
