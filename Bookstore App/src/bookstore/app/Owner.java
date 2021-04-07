package bookstore.app;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.io.PrintWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Owner extends User {

    // Associated file: books.txt
    // Associated file: customer.txt 
    public static final String ownerPassword = "admin";
    public static final String ownerUsername = "admin";

    private static Owner instance = null;
    private int capacity;
    
    private Customer customerInstance;

    private Owner(String customerFile, String bookFile) {
        super(ownerPassword, ownerUsername);
        readFromCustomerList(customerFile);
        readFromBookList(bookFile);
    }

    // Read owner input and create customer object and  
    // Add to arraylist
    public void readFromCustomerList(String filename) {
        File file = new File(filename);
        
        try {
            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String array[] = line.split(",");
                Customer c = new Customer(array[0], array[1], Integer.parseInt(array[2]));
                customersList.add(c);
            }
        } 
        catch (FileNotFoundException ex) {
            System.out.println("Customer not found");
        }
    }

    public void readFromBookList(String filename) {
        File file = new File(filename);
        try {
            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String array[] = line.split(",");
                Book b = new Book(array[0], Double.parseDouble(array[2]));
                bookList.add(b);
            }
        } 
        catch (FileNotFoundException ex) {
            System.out.println("Book not found");
        }
    }

    public void writeToCustomerList(String filename) {
        try {
            FileWriter r = new FileWriter(filename);
            for (Customer c : customersList) {
                r.write(String.format("%s,%s,%d", c.getPassword(), c.getUsername(), c.getPoints()));
            }
            r.close();
        } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeToBookList(String filename) {
        try {
            FileWriter r = new FileWriter(filename);
            for (Book b : bookList) {
                r.write(String.format("%s,%.2f", b.getName(), b.getPrice()));
            }
            r.close();
        } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace(); //what does this do?
        }
    }

    public static Owner getInstance(String customerFile, String bookFile) {
        if (instance == null) {
            instance = new Owner(customerFile,bookFile);
        }
        return instance;
    }

    public void addCustomer(Customer c) {
        customersList.add(c);
    }

    public void removeCustomer(Customer toRemove) {
        customersList.remove(toRemove);
    }

    public Customer getCustomer(int index) {
        return customersList.get(index);
    }

    public void addBook(Book b) {
        bookList.add(b);
    }

    public void removeBook(Book toRemove) {
        bookList.remove(toRemove);
    }

    public ArrayList<Customer> getCustomersList() {
        return customersList;
    }

    public void printInfo() { }
}


 
