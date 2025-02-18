import java.util.Scanner;

public class vendingMachineSim {
    public static void main(String[] args) {


        int code[] = {1, 2, 3, 4, 5};
        String name[] = {"Soda", "Water", "Chips", "Chocolate", "Soleti"};
        double price[] = {2.5, 1.0, 3.3, 4, 1.7};
        int quantity[] = {10, 15, 8, 4, 12};


        Scanner sc = new Scanner(System.in);
        System.out.print("Entrer balance: ");
        double balance = sc.nextInt();
        System.out.println("Balance: " + balance + "$");
        System.out.println("Num. Name  |  Price |  Quantity");
        printAll(code, name, price, quantity);
        System.out.print("Enter Num: ");
        String br = "";
        int numberOfItem = 0;
        int a =0;
        int num = 0;



            while(!br.equals("Y")) {
                num = sc.nextInt();
                for (int i : code) {

                    if (i == num) {
                        numberOfItem = i - 1;
                        if (balance >= price[numberOfItem]) {
                            balance = balance - price[numberOfItem];
                            a = 1;
                        } else System.out.println("You can't purchase this item");
                        System.out.println("Balance: " + balance + "$");
                        if (quantity[numberOfItem] <= 0) {
                            System.out.println("There are no more " + name[numberOfItem] + " items");

                        }
                        quantity[numberOfItem] = quantity[numberOfItem] - a;
                        printAll(code, name, price, quantity);
                        System.out.println("Do you want to quit? (Y/N): ");
                        br = sc.next();
                        System.out.print("Enter Num: ");


                    }
                }

            }

    }
    public static void printAll(int[] code, String[] name, double[] price, int[] quantity){
        for(int i = 0; i < name.length; i++){
            System.out.println(code[i] + ".   " + name[i] + "  |  " + price[i] + "  |  " + quantity[i]);
        }
    }



}
