class compositionTask {
    public static void main(String[] args) {
        // Task 5: Create an Engine object with a type of "V8".
        Engine engine = new Engine("V8");
        // Task 6: Create a Car object with the brand "Ford", the Engine object, and wheel size of 18 inches.
        Brand brand = new Brand("Ferrari 488");
        Wheel frontWheel = new Wheel("245x35 R20");
        Wheel rearWheel = new Wheel("305x30 R20");
        Drivetrain drivetrain = new Drivetrain("RWD");
        Transmission transmission = new Transmission("7-speed dual-clutch automatic");

        Car car = new Car(brand, engine, frontWheel, rearWheel, drivetrain, transmission );
        // Task 7: Call the displayInfo() method to print the car's details.
        car.displayInfo();
    }
}

class Brand {
    private String brand;

    public Brand(String brand) {
        this.brand = brand;
    }
    public String getBrand() {
        return brand;
    }

}

class Engine {
    private String type;

    public Engine(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

class Wheel {
    private String size;

    public Wheel(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}

class Drivetrain {
    private String Drivetrain;

    public Drivetrain(String drivetrain) {
        this.Drivetrain = drivetrain;
    }

    public String getDrivetrain() {
        return Drivetrain;
    }
}

class Transmission {
    private String type;

    public Transmission(String Transmission) {
        this.type = Transmission;
    }

    public String getTransmission() {
        return type;
    }
}

class Car {
    private Brand brand;
    private Engine engine;
    private Wheel frontWheels;
    private Wheel backWheels;
    private Drivetrain drivetrain;
    private Transmission transmission;
    // Task 1: Add three more wheel objects (frontRightWheel, rearLeftWheel, rearRightWheel).

    public Car(Brand brand, Engine engine, Wheel frontWheelSize, Wheel backWheelSize, Drivetrain drivetrain, Transmission transmission) {
        this.brand = brand;
        this.engine = engine;
// Task 2: Initialize all the Wheel objects inside the constructor.
        this.frontWheels = frontWheelSize;
        this.backWheels = backWheelSize;
        this.drivetrain = drivetrain;
        this.transmission = transmission;



    }

    public void displayInfo() {
        System.out.println("Car Brand: " + brand.getBrand());
        System.out.println("Engine Type: " + engine.getType());
        System.out.println("Front Wheels Size: " + frontWheels.getSize());
        System.out.println("Back Wheels Size: " + backWheels.getSize());
        System.out.println("Drivetrain: " + drivetrain.getDrivetrain());
        System.out.println("Transmission: " + transmission.getTransmission());
        System.out.println("----------------------------------------");


        // Task 3: Add print statements for the sizes of the other three wheels.
    }




}