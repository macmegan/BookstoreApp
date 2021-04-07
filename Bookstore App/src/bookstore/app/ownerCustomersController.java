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
    @FXML Button addCustomer;
    @FXML Button deleteCustomer;
    @FXML Button back;
    @FXML TableView<Customer> customerTableView;
    @FXML private TableColumn<Customer,String> customerUsernameCol;
    @FXML private TableColumn<Customer,String> customerPasswordCol;
    @FXML private TableColumn<Customer,String> customerPointsCol;
    @FXML protected TextField enterUsername;
    @FXML protected PasswordField enterPassword; 
    
    /**
     * Changes owner book screen back to owner start screen
     * @param event
     * @throws IOException 
     */
    public void backButton (ActionEvent event) throws IOException {
        Parent goBackParent = FXMLLoader.load(getClass().getResource("ownerStart.fxml"));
        Scene goBack = new Scene(goBackParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(goBack);
        window.show();
    } 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerUsernameCol.setCellValueFactory(new PropertyValueFactory<Customer,String>("username"));
        customerPasswordCol.setCellValueFactory(new PropertyValueFactory<Customer,String>("password"));
        customerPointsCol.setCellValueFactory(new PropertyValueFactory<Customer,String>("points"));
        
        customerTableView.setItems(getCustomers());
    }
    
    /**
     * Adds new customer object to table
     */
    public void addCustomers() throws IOException {
        Customer newCustomer = new Customer(enterUsername.getText(), enterPassword.getText(), 0);
        customerTableView.getItems().add(newCustomer);
        BufferedWriter writer = new BufferedWriter(new FileWriter("src//bookstore//app//Customers.txt", true));
        
        writer.append("\n" + enterUsername.getText() + " " + enterPassword.getText() + " 0");
        
        writer.close();
    }
    
    /**
     * Deletes existing customer object from table
     */
    public void deleteCustomers() throws IOException {
        ObservableList<Customer> selectedRows, allCustomers;
        allCustomers = customerTableView.getItems();
        
        selectedRows = customerTableView.getSelectionModel().getSelectedItems();
        
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
        } catch(FileNotFoundException e){
            System.out.println(e);
        }
        
        return customers;
    }  
    
}
