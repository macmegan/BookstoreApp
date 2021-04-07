package bookstore.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Megan Mac
 */
public class ownerCustomersController implements Initializable {
    // FXML DECLARATIONS
    // Buttons for user interaction
    @FXML Button addCustomer;
    @FXML Button deleteCustomer;
    @FXML Button back;
    
    // Initialise table
    @FXML TableView<Customer> customerTableView;
    @FXML private TableColumn<Customer,String> customerUsernameCol;
    @FXML private TableColumn<Customer,String> customerPasswordCol;
    @FXML private TableColumn<Customer,String> customerPointsCol;
    
    // Text fields for user input
    @FXML protected TextField enterUsername;
    @FXML protected PasswordField enterPassword; 
    
    /**
     * Changes owner book screen back to owner start screen when back button is pushed
     * @param event
     * @throws IOException 
     */
    public void backButton (ActionEvent event) throws IOException {
        // Load bookstore owner start menu screen
        Parent goBackParent = FXMLLoader.load(getClass().getResource("ownerStart.fxml"));
        Scene goBack = new Scene(goBackParent);
        
        // Get stage information and switch scenes
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(goBack);
        window.show();
    } 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set up columns in the table
        customerUsernameCol.setCellValueFactory(new PropertyValueFactory<Customer,String>("username"));
        customerPasswordCol.setCellValueFactory(new PropertyValueFactory<Customer,String>("password"));
        customerPointsCol.setCellValueFactory(new PropertyValueFactory<Customer,String>("points"));
        
        // Load customer data
        customerTableView.setItems(getCustomers());
    }
    
    /**
     * Adds new customer object(s) to table when add button is pushed
     */
    public void addCustomers() throws IOException {
        // Create new customer using info from user input text fields and set points to 0
        Customer newCustomer = new Customer(enterUsername.getText(), enterPassword.getText(), 0);
        customerTableView.getItems().add(newCustomer); // Add new customer to table
        
        BufferedWriter writer = new BufferedWriter(new FileWriter("src//bookstore//app//Customers.txt", true));
        writer.append("\n" + enterUsername.getText() + " " + enterPassword.getText() + " 0");
        writer.close();
    }
    
    /**
     * Deletes existing customer object(s) from table
     */
    public void deleteCustomers() throws IOException {
        // Create observable list to display updated customer data in table
        ObservableList<Customer> selectedRows, allCustomers;
        allCustomers = customerTableView.getItems();
        
        // Retrieve table rows selected by user
        selectedRows = customerTableView.getSelectionModel().getSelectedItems();
        
        // Loop over selected rows and remove customer objects from table
        for (Customer customer: selectedRows) {
            allCustomers.remove(customer);
        }
        
        FileWriter writeCust = new FileWriter("src//bookstore//app/Customers.txt");
        
        for (Customer cust : allCustomers){
            writeCust.write(cust.getUsername() + " " + cust.getPassword() + " " + cust.getPoints() + System.lineSeparator());    
        }
        writeCust.close();
    }
    
    /**
     * Returns ObservableList of Customer objects
     * @returns customers;
     */
    public ObservableList<Customer> getCustomers(){
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        
        try {
            File myCust = new File("src//bookstore//app//Customers.txt");
            Scanner readin = new Scanner(myCust);
            while(readin.hasNextLine()){
                String[] CustInfo = readin.nextLine().split(" ");
                customers.add(new Customer(CustInfo[0], CustInfo[1], Integer.parseInt(CustInfo[2])));
            } 
        } 
        catch(FileNotFoundException e){
            System.out.println(e);
        }
        
        return customers;
    }
}
