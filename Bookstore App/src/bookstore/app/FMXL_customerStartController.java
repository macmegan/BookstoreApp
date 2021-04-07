package bookstore.app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;
import javafx.scene.control.cell.CheckBoxTableCell;

/**
 * FXML Controller class
 *
 * @author anika
 */
public class FMXL_customerStartController implements Initializable {
    // FXML DECLARATIONS
    // User interactive buttons
    @FXML Button BuyBut;
    @FXML Button RedeemBuy;
    @FXML Button logout;
    
    // Initialise table
    @FXML TableView tableview;
    
    // Display customer info
    @FXML private Label username;
    @FXML private Label points;
    @FXML private Label password;
    @FXML TextArea usertxt;
    @FXML TextArea statustxt;
    @FXML TextArea pointstxt;
    
    /**
     * Initiates a purchase when user presses the buy button
     */
    public void Buy(ActionEvent event) throws IOException {
        // Variable declaration
        double totalcost = 0;
        int points = 0;
        String oldpoints = "";
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Customer c = (Customer) window.getUserData();
        
        // Create Arraylist to store customer data
        ArrayList<String> custlist = new ArrayList<>();    
        
        // Create file object and read in customer data
        File myObj = new File("src//bookstore//app//Customers.txt");
        try (Scanner read = new Scanner(myObj)) {
            while(read.hasNextLine()){ 
                String data = read.nextLine();
                custlist.add(data);
            }
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }
        
        // Check if current customer's username and password equals to the one in
        // the current line of Customers.txt and get points
        for(String i : custlist){
            String[] custlist2 = i.split(" ");
            
            if(c.getUsername().equals(custlist2[0]) && c.getPassword().equals(custlist2[1])){
                points = Integer.parseInt(custlist2[2]);
                oldpoints = custlist2[2];
            }
        }
        
        // Get selected items and put them in an observable arraylist
        // Increase point and total cost accordingly
        ObservableList<Book> booksList = FXCollections.observableArrayList();
            tableview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            ObservableList<Book> item = tableview.getSelectionModel().getSelectedItems();
            for (Book b : item){
                booksList.add(b);
                points += b.price*10;
                totalcost += b.price;
            }
        
        // Write total cost to file to share with customer cost screen
        FileWriter totalwritecost = new FileWriter("src//bookstore//app//TotalCost.txt", false);
        totalwritecost.write(String.valueOf(totalcost));
        totalwritecost.close();
        c.points = points;
        
        // Update customer status based on current points
        if (points < 1000){
            c.setStatus(c.getSilver());
        }
        else
            c.setStatus(c.getGold());
        
        // Update user's points in customers.txt
        // Using buffer to read because it allows making changes without rewriting the file
        Scanner sc = new Scanner(new File("src//bookstore//app//Customers.txt"));
        StringBuffer buffer = new StringBuffer();
        
        while (sc.hasNextLine()){
            buffer.append(sc.nextLine()+System.lineSeparator());
        }
        
        String fileContents = buffer.toString();
        sc.close();
        String oldline = c.getUsername() + " " + c.getPassword() + " " + oldpoints;
        String newline = c.getUsername() + " " + c.getPassword() + " " + points;
        fileContents = fileContents.replaceAll(oldline, newline);
        
        FileWriter write = new FileWriter("src//bookstore//app//Customers.txt");
        write.append(fileContents);
        write.flush();
        
        // Change scene to transaction summary screen
        Parent buyOrRedeemParent = FXMLLoader.load(getClass().getResource("userCost.fxml"));
        Scene buyOrRedeem = new Scene(buyOrRedeemParent);
        
        Stage window2 = (Stage)((Node)event.getSource()).getScene().getWindow();
        window2.setUserData(totalcost);
        window2.setScene(buyOrRedeem);
        window2.show();
    }
    
    /**
     * Initates points redemption when user pushes buy and redeem button
     */
    public void RedeemBuy(ActionEvent event) throws IOException {
        // Variable declaration
        double totalcost = 0;
        double bookcost;
        int reduction = 0;
        int points = 0;
        String oldpoints = "";
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Customer c = (Customer) window.getUserData();
        
        // Create Arraylist to store customer data
        ArrayList<String> custlist = new ArrayList<>();    
        
        // Create file object and read in customer data
        File myObj = new File("src//bookstore//app//Customers.txt");
        try (Scanner read = new Scanner(myObj)) {
            while(read.hasNextLine()){ 
                String data = read.nextLine();
                custlist.add(data);
            }
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }
        
        // Verify customer login credentials using Customers.txt and retrieve point balance
        for(String i : custlist){
            String[] custlist2 = i.split(" ");
            
            if(c.getUsername().equals(custlist2[0]) && c.getPassword().equals(custlist2[1])){
                points = Integer.parseInt(custlist2[2]);
                oldpoints = custlist2[2];
            }
        }
        
        // Get selected items and put them in an observable arraylist
        ObservableList<Book> booksList = FXCollections.observableArrayList();
        tableview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList<Book> item = tableview.getSelectionModel().getSelectedItems();

        // Adjust point balance and total cost based on product selection
        for (Book b : item){
            booksList.add(b);
            bookcost = b.price;
            totalcost+=bookcost;
            
            // Calculate points eligible for redemption given book cost and point balance
            while(points > 99 && bookcost > 0){
                points -= 100;
                bookcost -= 1;
                reduction++;
            }
        }
        
        // Write total cost to file to share with transaction summary screen
        // Account for reduction in total cost from point redemption
        totalcost -= reduction; 
        FileWriter totalwritecost = new FileWriter("src//bookstore//app//TotalCost.txt", false);
        totalwritecost.write(String.valueOf(totalcost));
        totalwritecost.close();
        c.points = points;
        
        // Update customer status based on current points
        if (points < 1000){
            c.setStatus(c.getSilver());
        }
        else
            c.setStatus(c.getGold());
        
        // Update user's points in customers.txt
        // Using buffer to read because it allows making changes without rewriting the file       
        Scanner sc = new Scanner(new File("src//bookstore//app//Customers.txt"));
        StringBuffer buffer = new StringBuffer();
        
        while (sc.hasNextLine()){
            buffer.append(sc.nextLine()+System.lineSeparator());
        }
        
        String fileContents = buffer.toString();
        sc.close();
        String oldline = c.getUsername() + " " + c.getPassword() + " " + oldpoints;
        String newline = c.getUsername() + " " + c.getPassword() + " " + points;
        fileContents = fileContents.replaceAll(oldline, newline);
        
        FileWriter write = new FileWriter("src//bookstore//app//Customers.txt");
        write.append(fileContents);
        write.flush();
        
        // Change scene to transaction summary screen
        Parent buyOrRedeemParent = FXMLLoader.load(getClass().getResource("userCost.fxml"));
        Scene buyOrRedeem = new Scene(buyOrRedeemParent);
        
        Stage window2 = (Stage)((Node)event.getSource()).getScene().getWindow();
        window2.setUserData(totalcost);
        window2.setScene(buyOrRedeem);
        window2.show();
    }
    
    /**
     * Returns user to login screen when the logout button is pushed
     */
    public void logout (ActionEvent event) throws IOException {
        // Load scene
        Parent logoutParent = FXMLLoader.load(getClass().getResource("userLogin.fxml"));
        Scene logout = new Scene(logoutParent);
        
        // Change scene to login screen
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(logout);
        window.show();
    } 
  
    /**
     * Initialises the controller class
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        String pointsnew = "";
        File myobj = new File("src//bookstore//app//CurrentCustomer.txt");
        
        try {
            Scanner read = new Scanner(myobj);
            String[] data = read.nextLine().split(" ");
            ArrayList<String> custlist = new ArrayList<>();
        
            File myObj = new File("src//bookstore//app//Customers.txt");
        
            try (Scanner readn = new Scanner(myObj)) {
                while(readn.hasNextLine()){ 
                    String datan = readn.nextLine();
                    custlist.add(datan);
                }
            }
            catch(FileNotFoundException e){
                System.out.println(e);
            }
        
            for(String i : custlist){
                String[] custlist2 = i.split(" ");
                
                if(data[0].equals(custlist2[0]) && data[1].equals(custlist2[1]))
                    pointsnew = custlist2[2];
            }
            
            usertxt.setText(data[0]);
            pointstxt.setText(pointsnew);
            
            if(Double.parseDouble(pointsnew) > 1000)
                statustxt.setText("Gold");
            else
                statustxt.setText("Silver");  
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(FMXL_customerStartController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Create interactive table to enable users to select products for purchase
        tableview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        Book item = (Book) tableview.getSelectionModel().getSelectedItem();
        TableColumn bookname = new TableColumn("Name");
        TableColumn bookprice = new TableColumn("Price");
        TableColumn selectCol = new TableColumn("Select");
        tableview.getColumns().addAll(bookname, bookprice, selectCol);
        
        ObservableList<Book> books = FXCollections.observableArrayList();
        File myObj = new File("src//bookstore//app//Books.txt");
        
        try (Scanner read = new Scanner(myObj)) {
            while(read.hasNextLine()){ 
                String data = read.nextLine();
                String[] booksinfo = data.split(" ");
                books.add(new Book(booksinfo[0], Double.parseDouble(booksinfo[1])));
            }
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }
        
        // Set up columns in the table
        bookname.setCellValueFactory(new PropertyValueFactory<>("name"));
        bookprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        selectCol.setCellValueFactory(new PropertyValueFactory<Book,CheckBox>("select"));
        selectCol.setCellFactory(CheckBoxTableCell.forTableColumn(selectCol));
        tableview.setItems(books);
    }    
}
    
    
    
    
    


