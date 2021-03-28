/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapp;
import java.io.PrintWriter;
import java.Scanner; 
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author jason
 */
public class Owner {
   Books bb;

private String name;

private int capacity;

private CustomerList<Customers> customers;



public User(){

//should have the username and password set to "admin" and only then it would log in?

}
public void readFrom(Scanner input){
//this should be reading the customers.txt maybe to print it in our table for the front end?

}

 public void writeTo(PrintWriter output) {

//here we write the new added customer information to the customer.txt which would be the username, password and point initially set to 0.

}



 public void addCustomer(Customer newCustomer) {

        customers.add(newCustomer);

//something related to connecting with FX

        FXCollections.sort(customers);

    }
public void removeCustomer(Customer toRemove) {

        customers.remove(toRemove);

    }





}
     
    }
}
