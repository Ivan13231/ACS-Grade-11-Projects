import java.util.Scanner;

public class BookShopSim {
    public static void main(String[] args) {


        int[] code = {1, 2, 3, 4, 5};
        String[] name = {"The Great Gatsby", "Animal Farm", "Lord of the Flies", "The Stranger", "In Cold Blood"};
        double[] price = {15.50, 14.72, 20.43, 18.95, 17.23};
        int[] quantity = {10, 15, 8, 16, 12};


        Scanner sc = new Scanner(System.in);
        System.out.print("Enter balance: ");
        double balance = sc.nextInt();
        printAll(code, name);
        findAndBuyBook(name, quantity, price, balance);







    }

    public static void printAll(int[] code, String[] name) {
        for (int i = 0; i < code.length; i++) {
            System.out.print(code[i] + " ");
            System.out.println(name[i] + " ");
        }


    }

    public static void findAndBuyBook(String[] name, int[] quantity, double[] price,double balance) {
        int i = -1;
        String yesOrNo = "";
        while (!yesOrNo.equals("Yes")) {
            i = -1;
            System.out.print("Enter the name of the book you want to buy: ");
            Scanner sc = new Scanner(System.in);
            String bookName = sc.nextLine();
            for (String nameOfBook : name) {
                i++;
                if (nameOfBook.equals(bookName)) {
                    System.out.println("We have " + quantity[i] + " copies of " + nameOfBook + ".");
                    System.out.println("Each copy costs " + price[i] + "$.");
                    System.out.println("Do you want to buy one?(Yes/No)");
                    yesOrNo = sc.nextLine();
                    if (yesOrNo.equals("Yes")) {
                        if (quantity[i] >= 1) {
                            if (balance >= price[i]) {
                                balance = balance - price[i];
                                quantity[i]--;
                                System.out.println("You have bought " + nameOfBook + ".");
                                System.out.println("Balance: " + balance + "$");
                            } else System.out.println("You don't have enough money.");
                        } else System.out.println("We don't have any more copies of this book.");

                    }
                    System.out.println("Do you want to exit the store?(Yes/No)");
                    yesOrNo = sc.nextLine();


                }
            }


        }
    }


}






//        System.out.println("Num. Name  |  Price |  Quantity");
//        printAll(code, name, price, quantity);
//        System.out.print("Enter Num: ");
//        String br = "";
//        int numberOfItem = 0;
//        int a =0;
//        int num = 0;
//
//
//
//        while(!br.equals("Y")) {
//            num = sc.nextInt();
//            for (int i : code) {
//
//                if (i == num) {
//                    numberOfItem = i - 1;
//                    if (balance >= price[numberOfItem]) {
//                        balance = balance - price[numberOfItem];
//                        a = 1;
//                    } else System.out.println("You can't purchase this item");
//                    System.out.println("Balance: " + balance + "$");
//                    if (quantity[numberOfItem] <= 0) {
//                        System.out.println("There are no more " + name[numberOfItem] + " items");
//
//                    }
//                    quantity[numberOfItem] = quantity[numberOfItem] - a;
//                    printAll(code, name, price, quantity);
//                    System.out.println("Do you want to quit? (Y/N): ");
//                    br = sc.next();
//                    System.out.print("Enter Num: ");
//
//
//                }
//            }
//
//        }
//
//    }
//    public static void printAll(int[] code, String[] name, double[] price, int[] quantity){
//        for(int i = 0; i < name.length; i++){
//            System.out.println(code[i] + ".   " + name[i] + "  |  " + price[i] + "  |  " + quantity[i]);
//        }
//    }
//
//
//
//}
//
