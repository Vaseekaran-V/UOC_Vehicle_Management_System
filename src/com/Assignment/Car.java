package com.Assignment;

public class Car extends Vehicle {
    private final int noOfDoors;
    private final int numOfLuggage;
    private final int numOfPassengers;
    private boolean airCon;
    private boolean musicPlayer;

    public Car(String plate, String make, String model, double ratePerDay, int noOfDoors, int numOfLuggage, int numOfPassengers, boolean airCon, boolean musicPlayer) {
        super(plate, make, model, ratePerDay);
        this.noOfDoors = noOfDoors;
        this.numOfLuggage = numOfLuggage;
        this.numOfPassengers = numOfPassengers;
        this.airCon = airCon;
        this.musicPlayer = musicPlayer;
    }

    public int getNoOfDoors() {
        return noOfDoors;
    }

    public int getNumOfLuggage() {
        return numOfLuggage;
    }

    public int getNumOfPassengers() {
        return numOfPassengers;
    }

    public boolean isAirCon() {
        return airCon;
    }

    public boolean isMusicPlayer() {
        return musicPlayer;
    }

    public void setAirCon(boolean airCon) {
        this.airCon = airCon;
    }

    public void setMusicPlayer(boolean musicPlayer) {
        this.musicPlayer = musicPlayer;
    }
}
