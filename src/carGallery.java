import java.util.Scanner;

public class carGallery {

    public static void main(String[] args) {

        Scanner scan  = new Scanner(System.in);
        Car[] carArray = {
                new Car("Ferrari", "F40", 1987, 2.6, false ),
                new Car("Mclaren", "P1", 2013, 2.4, false ),
                new Car("Porsche", "Carrera GT", 2004, 1.4, false ),
                new Car("Pagani", "Zonda R", 2009, 6.5, false ),
        };
        int choice = 0;
        System.out.println("Welcome to Car Gallery!");
        displayAll(carArray);
        System.out.println("These are our available cars: ");
        displayAvailableCars(carArray);
        System.out.println("This is our most expensive car: ");
        mostExpensiveCar(carArray).displayInfo();
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.println("This is the average price of our cars: " + averagePrice(carArray) + " Million $");
        do {
            System.out.println("Enter the option: ");
            choice = scan.nextInt();
            leaseCar(carArray, choice);
            displayAvailableCars(carArray);
        }
        while (choice != 0);
        displayAll(carArray);
    }

    public static void displayAvailableCars(Car[] car){
        System.out.println("--------------------------------------------------------------------------------------------------------");
        for(Car element : car){
            if(element.getIsLeased() == false ){
                element.displayInfo();
            }

        }
        System.out.println("--------------------------------------------------------------------------------------------------------");
    }

    public static void displayAll(Car[] car){
        System.out.println("--------------------------------------------------------------------------------------------------------");
        for(Car element : car){
            element.displayInfo();
        }
        System.out.println("--------------------------------------------------------------------------------------------------------");
    }

    public static Car mostExpensiveCar(Car[] car){
        System.out.println("--------------------------------------------------------------------------------------------------------");
        double highPrice = 0;
        Car result = null;

        for(Car element : car){
            if(element.getIsLeased() == false ){
                if(element.getPrice()>highPrice){
                    highPrice = element.getPrice();
                }
            }
        }

        for(Car element : car) {
            if (element.getIsLeased() == false) {
                if (element.getPrice() == highPrice) {

                    return element;
                }
            }
        }


        return result;
    }

    public static double averagePrice(Car[] car){
        double averagePrice = 0;
        int i = 0;
        for (Car element : car){
            averagePrice = averagePrice + element.getPrice();
            i++;
        }
        averagePrice = averagePrice/i;
        return averagePrice;
    }

    public static void leaseCar(Car[] car, int choice){
        int i = 0;
        for(Car elements : car ){
            i++;
            if(i == choice){
                elements.setIsLeased(true);
            }
        }
    }


}

class Car {
    String make;
    String model;
    int year;
    double price;
    boolean isLeased;

        public Car (String make, String model, int year, double price, boolean isLeased){
            this.make = make;
            this.model = model;
            this.year = year;
            this.price = price;
            this.isLeased = isLeased;

        }

        public void displayInfo(){
            System.out.print("Make : " + make);
            System.out.print("     Model : " + model);
            System.out.print("     Year : " + year);
            System.out.print("     Price : " + price +" Million $");
            System.out.println("     isLeased : " + isLeased);

        }

        public boolean getIsLeased(){
            return isLeased;
        }

        public void setIsLeased(boolean isLeased){
            this.isLeased = isLeased;
        }

        public double getPrice(){
            return price;
        }

}


