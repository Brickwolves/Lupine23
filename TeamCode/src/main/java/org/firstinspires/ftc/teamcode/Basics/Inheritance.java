package org.firstinspires.ftc.teamcode.Basics;

/*

        U N F I N I S H E D

 */



public class Inheritance {
    public void main(){
        String[] items2Load = {"bike", "skateboard"};
        Truck my_truck = new Truck("Jamie", 2, 36, items2Load);

        // WHOA, I can use a method that belongs to the Vehicle class??? Whaaaat
        my_truck.honk();
    }

    public class Vehicle {

        private String owner;
        private int gallons;
        private int age;

        public Vehicle(String input_owner, int input_age, int input_gallons){
            owner   = input_owner;
            age     = input_age;
            gallons = input_gallons;
        }
        public void honk(){
            System.out.println("HONKKKKKKKk");
        }
    }

    public class Truck extends Vehicle{

        // Hello I am an attribute! (A variable of a class - I can be accessed by any method of this class)
        private String[] loadedItems = new String[10];

        public Truck(String input_owner, int input_age, int input_gallons, String[] items){
            /*
                    Hey Collin, what does super() do?
             */
            super(input_owner, input_age, input_gallons);

            loadItems(items);
        }
        public void printAllItems(){
            // GOAL: Use a loop to print through all non-null items
            // PARAMETER: void
            // HINT: You can check if something is null like so:
            //  String[] arrayOfStrings = new String[3];
            //  if (arrayOfStrings[0] == null) {
            //      // do stuff
            //  }
        }
        public int getNumItems(){
            // GOAL: Retrieve number of items in the list loadedItems
            // CORE PROBLEM: loadedItems.length will return 10 so that doesn't work
            // HINT: Use a loop to count all items
            return 0;
        }
        public boolean isTruckBedFull(){
            // GOAL: Return a true/false based on whether the loadedItems is full
            // PARAMETER: void
            // HINT: Use getNumItems() and a conditional
            return true;
        }
        public void loadItems(String[] items){
            // GOAL: Figure out how to load items without exceeding limit
            // CORE PROBLEM: You don't know what the length of 'items' is
            // PARAMETER: List of Items
        }
    }


    public class ElectricCar extends Vehicle {

        private int recharging_time;
        private int max_distance_per_charge;
        public ElectricCar(String input_owner, int input_age, int input_gallons) {
            super(input_owner, input_age, input_gallons);
        }
    }


    // 1) Make truck class
        // Go through private and public
        // Methods
            // drive method
            // honk
            // load gas
            // loadItems (I list them out)
        // Attributes
            // max_gallons
            // number of passengers
            // company
            //-----------
            // truck bed
    // 2) make electric car class
        // Methods
            // drive method
            // load gas
            // honk
            // ------------
            // charge
        // Attributes
            // max_charge
            // charging_time
    // 3) vehicle
        // Go over super
        // Methods
            // drive method
            // honk
            // load gas
        // Attributes
            // max_gallons
            // number of passengers
            // company

    // remove drive, gallon, honk from truck and electric car
    // maybe show overriding



}
