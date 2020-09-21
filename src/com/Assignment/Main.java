package com.Assignment;

import java.util.ArrayList;
import java.util.Scanner;

public class Main implements Admin_Interface {

    private ArrayList<Vehicle> vehicleList = new ArrayList<>();

    @Override
    public void addVehicle(Vehicle vehicle) {
        vehicleList.add(vehicle);
    }

    @Override
    public void deleteVehicle(Vehicle vehicle) {
        vehicleList.remove(vehicle);
    }

    @Override
    public void viewVehicles() {
        ArrayList<Car> cars = new ArrayList<>();
        ArrayList<Bike> bikes = new ArrayList<>();
        for(Vehicle vehicle : vehicleList){
            if(vehicle instanceof Car){
                cars.add((Car)vehicle);
            }else if(vehicle instanceof Bike){
                bikes.add((Bike) vehicle);
            }
        }

        for(Car car: cars){
            System.out.println(car);
        }

        for(Bike bike: bikes){
            System.out.println(bike);
        }

        cars.clear();
        bikes.clear();
    }

    @Override
    public void saveVehicle() {

    }

    public void displayMenu(){
        Scanner sc = new Scanner(System.in);

        while(true){

            System.out.println("\n+-----WELCOME TO THE COLOMBO VEHICLE RENTAL MANAGER-----+");
            System.out.println("\n\t\t<----------MENU - MANAGER CONSOLE---------->\n");
            System.out.println("\t\t\t1. Add a Vehicle. \n\t\t\t2. View all vehicles. \n\t\t\t3. Delete Vehicle. \n\t" +
                    "\t\t4. Save Vehicle. \n\t\t\t5. Open GUI. \n\t\t\t6. Exit Program");
            System.out.println("\n\t\t**** ENTER YOUR CHOICE ****");

            while (!sc.hasNextInt()) {
                System.out.println("\t\t\tIncorrect type of input.\n\t\t\tChoose a number input from 1 to 6");
                sc.next();

            }
            int choice = sc.nextInt();

            switchCase(choice);
        }
    }

    public void switchCase(int choice){
        switch(choice){
            case 1:
                System.out.println("You have chosen to add a Vehicle");
                addVehicleProcess();
                break;

            case 2:
                System.out.println("You have chosen to view all the vehicles in the system");
                viewVehicles();
                break;

            case 3:
                System.out.println("You have chosen to delete a Vehicle");
                break;

            case 4:
                System.out.println("Opening the GUI...");
                break;

            case 6:
                System.out.println("You have chosen to exit the program. \nHave a great day!");
                System.exit(0);
                break;

            default:
                System.out.println("Incorrect range of option, try again between 1-6");
                displayMenu();
        }
    }

    public ArrayList<String> platesOfVehicles(){
        ArrayList<String> plates = new ArrayList<>();
        for(Vehicle vehicle: vehicleList){
            plates.add(vehicle.getPlate());
        }

        return plates;
    }

    public boolean checkPlateExist(String newPlate){
        ArrayList<String> plates = platesOfVehicles();
        for(String plate:plates){
            if(plate.equals(newPlate)){
                System.out.println("The plate already exists in the system.");
                return true;
            }
        }
        return false;
    }

    public void addVehicleProcess(){
        Scanner sc = new Scanner(System.in);

        String plate = inputPlate();
        String make = inputMake();
        String model = inputModel();
        double ratePerDay = inputRatePerDay();

        int carOrBike = chooseCarOrBike();

        chooseCarOrBikeProcess(carOrBike, plate, make, model, ratePerDay);
        displayMenu();

    }

    public void chooseCarOrBikeProcess(int choice,String plate, String make, String model, double ratePerDay ){
        if(choice == 1){
            addCarProcess(plate, make, model, ratePerDay);
        }else if(choice == 2){
            addBikeProcess(plate, make, model, ratePerDay);
        }
    }

    private void addBikeProcess(String plate, String make, String model, double ratePerDay) {
        String type = inputTypeOfBike();
        boolean isHelmet = isHelmet();
        boolean isJacket = isJacket();

        Vehicle bike = new Bike(plate, make, model, ratePerDay, type, isHelmet, isJacket);
        addVehicle(bike);
        System.out.println("The bike with plate " + bike.getPlate() + " has been added successfully to the system.");
    }

    public void addCarProcess(String plate, String make, String model, double ratePerDay){
        int numOfDoors = inputNumOfDoors();
        int numOfLuggage = inputNumOfLuggage();
        int numOfPassengers = inputNumOfPassengers();
        boolean isAirCon = isAirCon();
        boolean isMusicPlayer = isMusicPlayer();

        Vehicle car = new Car(plate, make, model, ratePerDay, numOfDoors, numOfLuggage, numOfPassengers, isAirCon, isMusicPlayer);
        addVehicle(car);
        System.out.println("The car with plate " + car.getPlate() + " has been added successfully to the system.");

    }

    public String inputPlate(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the plate number of the vehicle that you are going to add: ");
        String plate = sc.nextLine();
        while(checkPlateExist(plate)){
            System.out.println("Enter a new plate number of the vehicle");
            plate = sc.nextLine();
        }

        return plate;

    }

    public String inputMake(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the make of the vehicle: ");
        String make = sc.nextLine();
        return make;

    }

    public String inputModel(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the model of the vehicle: ");
        String model = sc.nextLine();
        return model;

    }



    public double inputRatePerDay(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the rate per day for the vehicle: ");
        double ratePerDay = -1.0;

        while(true){
            try{
                ratePerDay = sc.nextDouble();
                return ratePerDay;
            }catch (Exception e){
                System.out.println("Incorrect type of exception");
                System.out.println("Enter the rate per day for the vehicle correctly: ");
                sc.next();

            }
        }

    }

    public int inputNumOfDoors(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of doors for the car: ");
        int numOfDoors = -1;

        while(true){
            try{
                numOfDoors = sc.nextInt();
                return numOfDoors;
            }catch (Exception e){
                System.out.println("Incorrect type of exception");
                System.out.println("Enter the number of doors for the car correctly: ");
                sc.next();

            }
        }

    }

    public int inputNumOfLuggage(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of luggage for the car: ");
        int numOfLuggage = -1;

        while(true){
            try{
                numOfLuggage = sc.nextInt();
                return numOfLuggage;
            }catch (Exception e){
                System.out.println("Incorrect type of exception");
                System.out.println("Enter the number of luggage for the car correctly: ");
                sc.next();

            }
        }

    }

    public int inputNumOfPassengers(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of passengers for the car: ");
        int numOfPassengers = -1;

        while(true){
            try{
                numOfPassengers = sc.nextInt();
                return numOfPassengers;
            }catch (Exception e){
                System.out.println("Incorrect type of exception");
                System.out.println("Enter the number of passengers for the car correctly: ");
                sc.next();

            }
        }

    }

    public boolean isAirCon(){
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        System.out.println("Does the car have Air Conditioning: Press 1 if Yes || Press 2 if No: ");

        while(true){
            try{
                choice = sc.nextInt();
                if(choice == 1){
                    return true;
                }else if(choice == 2){
                    return false;
                }else{
                    System.out.println("Incorrect choice of input. Try again. Press 1 if AC is available || Press 2 if AC is unavailable:");
                }
            }catch (Exception e){
                System.out.println("Incorrect type of input entered.");
                System.out.println("Does the car have Air Conditioning: Press 1 if Yes || Press 2 if No: ");
                sc.next();
            }
        }

    }

    public boolean isMusicPlayer(){
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        System.out.println("Does the car have Music Player: Press 1 if Yes || Press 2 if No: ");

        while(true){
            try{
                choice = sc.nextInt();
                if(choice == 1){
                    return true;
                }else if(choice == 2){
                    return false;
                }else{
                    System.out.println("Incorrect choice of input. Try again. Press 1 if Music Player is available || Press 2 if Music Player is unavailable:");
                }
            }catch (Exception e){
                System.out.println("Incorrect type of input entered.");
                System.out.println("Does the car have Music Player: Press 1 if Yes || Press 2 if No: ");
                sc.next();
            }
        }

    }

    public String inputTypeOfBike(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the type of the bike: ");
        String type = sc.nextLine();
        return type;

    }

    public boolean isHelmet(){
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        System.out.println("Does the bike come with the helmet: Press 1 if Yes || Press 2 if No: ");

        while(true){
            try{
                choice = sc.nextInt();
                if(choice == 1){
                    return true;
                }else if(choice == 2){
                    return false;
                }else{
                    System.out.println("Incorrect choice of input. Try again. Press 1 if helmet is available || Press 2 if helmet is unavailable:");
                }
            }catch (Exception e){
                System.out.println("Incorrect type of input entered.");
                System.out.println("Does the bike come with the helmet: Press 1 if Yes || Press 2 if No: ");
                sc.next();
            }
        }

    }

    public boolean isJacket(){
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        System.out.println("Does the bike come with the jacket: Press 1 if Yes || Press 2 if No: ");

        while(true){
            try{
                choice = sc.nextInt();
                if(choice == 1){
                    return true;
                }else if(choice == 2){
                    return false;
                }else{
                    System.out.println("Incorrect choice of input. Try again. Press 1 if jacket is available || Press 2 if jacket is unavailable:");
                }
            }catch (Exception e){
                System.out.println("Incorrect type of input entered.");
                System.out.println("Does the bike come with the jacket: Press 1 if Yes || Press 2 if No: ");
                sc.next();
            }
        }

    }

    public int chooseCarOrBike(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Press 1 to add a Car || Press 2 to add a Bike");
        int carOrBike;

        while(true){
            try{
                carOrBike = sc.nextInt();
                if(carOrBike > 0 && carOrBike < 3){
                    return carOrBike;
                }else{
                    System.out.println("Incorrect choice. Enter 1 for Car || Enter 2 for Bike");
                }
            }catch (Exception e){
                System.out.println("Incorrect type of input");
                System.out.println("Press 1 to add a Car || Press 2 to add a Bike");
                sc.next();
            }
        }
    }

    public static void main(String[] args) {
//        Vehicle v1 = new Car("123", "Audi", "M2", 123, 2,4,2,true, true);
//        System.out.println(v1 instanceof Vehicle);
//        System.out.println(v1 instanceof Car);
//        System.out.println(v1 instanceof Bike);
//        Bike c1 = (Bike) v1;
//        c1.isHelmet();
        Main main = new Main();
        main.displayMenu();
    }
}