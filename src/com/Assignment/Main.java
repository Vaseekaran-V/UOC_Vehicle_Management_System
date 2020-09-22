package com.Assignment;

import java.lang.reflect.Array;
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

    //code to view the vehicles in the running system
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

        System.out.format("+----------------+----------------+-----------------+%n");
        System.out.format("|----------------|----VEHICLES----|-----------------|%n");

        displayCars(cars);
        displayBikes(bikes);

        cars.clear();
        bikes.clear();
    }

    //code to view the cars in the system: method is called in viewVehicles method
    public void displayCars(ArrayList<Car> cars){
        String leftAlignFormat = "|  %12s  | %13s  |  %13s  |%n";
        System.out.format("|----------------|----------------|-----------------|%n");
        System.out.format("|                       CARS                        |%n");
        System.out.format("|----------------|----------------|-----------------|%n");
        System.out.format("|----------------|----------------|-----------------|%n");
        System.out.format("|  Plate Number  |      Make      |        Model    |%n");
        System.out.format("|----------------|----------------|-----------------|%n");
        System.out.format("|----------------|----------------|-----------------|%n");

        for(Car car: cars){
            System.out.format(leftAlignFormat,car.getPlate(),car.getMake(),car.getModel());
            System.out.format("|----------------|----------------|-----------------|%n");
        }
    }

    //code to view the bikes in the system: method is called in viewVehicles method
    public void displayBikes(ArrayList<Bike> bikes){
        String leftAlignFormat = "|  %12s  | %13s  |  %13s  |%n";
        System.out.format("|----------------|----------------|-----------------|%n");
        System.out.format("|----------------|----------------|-----------------|%n");
        System.out.format("|                      BIKES                        |%n");
        System.out.format("|----------------|----------------|-----------------|%n");
        System.out.format("|----------------|----------------|-----------------|%n");
        System.out.format("|  Plate Number  |      Make      |        Model    |%n");
        System.out.format("|----------------|----------------|-----------------|%n");
        System.out.format("|----------------|----------------|-----------------|%n");

        for(Bike bike: bikes){
            System.out.format(leftAlignFormat,bike.getPlate(),bike.getMake(),bike.getModel());
            System.out.format("|----------------|----------------|-----------------|%n");
        }
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

    //switch block to decide on the user input
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
                deleteVehicleProcess();
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

    //getting the plate numbers in the system
    public ArrayList<String> platesOfVehicles(){
        ArrayList<String> plates = new ArrayList<>();
        for(Vehicle vehicle: vehicleList){
            plates.add(vehicle.getPlate());
        }

        return plates;
    }

    //checking if there is an existing plate
    public boolean checkPlateExist(String newPlate){
        ArrayList<String> plates = platesOfVehicles();
        for(String plate:plates){
            if(plate.equals(newPlate)){
                System.out.println("A vehicle with the plate " + newPlate + " exists in the system.");
                return true;
            }
        }
        return false;
    }

    public void deleteVehicleProcess(){
        Scanner sc = new Scanner(System.in);

        System.out.println("The vehicles present in the system");
        viewVehicles();

        System.out.println("Enter the plate of the vehicle you want to delete: ");
        String plate = sc.nextLine();

        if(checkPlateExist(plate)){
            System.out.println("Deleting the vehicle with the plate " + plate + "...");
            findAndDelete(plate);
            System.out.println("The vehicle with the plate " + plate + " is deleted from the system successfully");
        }else{
            System.out.println("The plate you have entered is not in the system");
        }

        displayMenu();

    }

    public void findAndDelete(String plate){
        for(int i = 0; i < vehicleList.size(); i++){
            if(vehicleList.get(i).getPlate().equals(plate)){
                vehicleList.remove(i);
            }
        }
    }

    //process of adding a vehicle to the system
    public void addVehicleProcess(){

        String plate = inputPlate();
        String make = inputMake();
        String model = inputModel();
        double ratePerDay = inputRatePerDay();

        int carOrBike = chooseCarOrBike();

        chooseCarOrBikeProcess(carOrBike, plate, make, model, ratePerDay);
        displayMenu();

    }

    //code where the user chooses to add a car or a bike
    public void chooseCarOrBikeProcess(int choice,String plate, String make, String model, double ratePerDay ){
        if(choice == 1){
            addCarProcess(plate, make, model, ratePerDay);
        }else if(choice == 2){
            addBikeProcess(plate, make, model, ratePerDay);
        }
    }

    //code when user wants to add a bike
    private void addBikeProcess(String plate, String make, String model, double ratePerDay) {
        String type = inputTypeOfBike();
        boolean isHelmet = isHelmet();
        boolean isJacket = isJacket();

        Vehicle bike = new Bike(plate, make, model, ratePerDay, type, isHelmet, isJacket); //creating a vehicle reference object with Bike constructor
        addVehicle(bike);//adding the created object to the system
        System.out.println("The bike with plate " + bike.getPlate() + " has been added successfully to the system.");
    }

    //code when user wants to add a car
    public void addCarProcess(String plate, String make, String model, double ratePerDay){
        int numOfDoors = inputNumOfDoors();
        int numOfLuggage = inputNumOfLuggage();
        int numOfPassengers = inputNumOfPassengers();
        boolean isAirCon = isAirCon();
        boolean isMusicPlayer = isMusicPlayer();

        Vehicle car = new Car(plate, make, model, ratePerDay, numOfDoors, numOfLuggage, numOfPassengers, isAirCon, isMusicPlayer); //creating a vehicle reference object with Car constructor
        addVehicle(car); //adding the created object to the system
        System.out.println("The car with plate " + car.getPlate() + " has been added successfully to the system.");

    }

    //getting the plate number of the vehicle when user inputs a vehicle to the system
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

    //code to input the make of the vehicle
    public String inputMake(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the make of the vehicle: ");
        String make = sc.nextLine();
        return make;

    }

    //code to input the model of the vehicle
    public String inputModel(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the model of the vehicle: ");
        String model = sc.nextLine();
        return model;

    }

    //code to input the rate per day of the vehicle
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

    //code to input the number of doors in a car
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

    //code to input the number of luggage in a car
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

    //code to input the number of passengers in a car
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

    //code to input whether the car has AC
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

    //code to input whether the car has a music player
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

    //code to input the type of bike
    public String inputTypeOfBike(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the type of the bike: ");
        String type = sc.nextLine();
        return type;

    }

    //code to input whether the bike provides helmet
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

    //code to input whether the bike provides a jacket
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

    //code to validate the input where the user chooses whether car or bike
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
        //initializing some vehicle objects to add to the list
        Vehicle v1 = new Car("anc", "Audi", "M2", 123, 2,4,2,true, true);
        Vehicle car1 = new Car("123", "aa", "b", 122, 2, 4, 2, true, true);

        Main main = new Main();
        //adding the initialized vehicles
        main.addVehicle(car1);
        main.addVehicle(v1);

        main.displayMenu();
    }
}
