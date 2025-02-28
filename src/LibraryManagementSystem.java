import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Book[] bookArray = {
                new Book("1984", "George Orwell", 1949, "Ivan"),
                new Book("To Kill a Mockingbird", "Harper Lee", 1960, null),
                new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, null),
                new Book("Moby-Dick", "Herman Melville", 1851, null),
                new Book("The Hobbit", "J.R.R. Tolkien", 1937, null),
        };


        System.out.println("Welcome to the Library!");
        displayAll(bookArray);
        System.out.println("Do you want to Borrow or Return a book: ");
        String borrowOrReturn = scan.nextLine();
        do {
            if (borrowOrReturn.equals("Borrow")) {
                borrowBook(bookArray, scan);
                displayAll(bookArray);
            } else {
                if (borrowOrReturn.equals("Return")) {
                    returnBook(bookArray, scan);
                    displayAll(bookArray);
                }
            }
            System.out.println(" ");
            System.out.println("Do you want to Borrow or Return a book: ");
            borrowOrReturn = scan.nextLine();
        }
        while(!borrowOrReturn.equals("No"));
    }

    public static void displayAll(Book[] book){
        for(Book element: book){
            element.displayInfo();
        }
    }

    public static void borrowBook(Book[] book, Scanner scan){
        String bookTitle = findBook(book, scan);
        if(bookTitle != null) {
            for (Book element : book) {
                if (bookTitle.equals(element.getTitle())) {
                    if (element.getBorrowerName() == null) {
                        System.out.println("We have the book. Enter your name: ");
                        String name = scan.nextLine();
                        element.setBorrowerName(name);
                    } else {
                        System.out.println("Sorry, this book is already borrowed.");
                    }
                }

            }
        }
        else {
            System.out.println("We don't have the book.");

        }



    }

    public static void returnBook(Book[] book, Scanner scan) {
        System.out.println("Enter your name: ");
        String name = scan.nextLine();
        String bookTitle = findBook(book, scan);
        if (bookTitle != null) {
            for (Book element : book) {
                if (bookTitle.equals(element.getTitle())) {
                    if (element.getBorrowerName().equals(name)) {
                        element.setBorrowerName(null);
                        System.out.println("Thank you for returning the book.");
                    }
                }
            }
        }
    }

    public static String findBook(Book[] book, Scanner scan){
        System.out.println("Enter the name of the book that you want to find: ");
        String titleChecker = scan.nextLine();
        String bookTitle = null;
        for(Book element: book){
            if(titleChecker.equals(element.getTitle())){
                element.displayInfo();
                bookTitle = element.getTitle();
            }
        }
    return bookTitle; }
}

class Book{
    String title;
    String author;
    int yearPublished;
    String borrowerName;

    public Book(String title, String author, int yearPublished, String borrowerName){
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.borrowerName = borrowerName;
    }

    public void displayInfo() {
        System.out.print("Title: " + title);
        System.out.print("   Author: " + author);
        System.out.print("   Year Published: " + yearPublished);
        if(borrowerName != null) {
            System.out.println("   Name of borrower: " + borrowerName);
        }
        else {
            System.out.println(" ");
        }

    }

    public String getTitle(){
        return title;
    }

    public String setBorrowerName(String borrowerName){
        this.borrowerName = borrowerName;
        return borrowerName;
    }

    public String getBorrowerName(){
        return borrowerName;
    }
}


