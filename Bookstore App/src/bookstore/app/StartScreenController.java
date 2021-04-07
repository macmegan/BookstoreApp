package bookstore.app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Megan Mac
 */
public class StartScreenController implements Initializable {
    // FXML DECLARATIONS
    // User interactive buttons
    @FXML private Button books;
    @FXML private Button customers;
    @FXML private Button logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     * Change scene to book management screen when books button is pressed
     */
    @FXML
    private void manageBooks(ActionEvent event) throws IOException {
        Parent ownerBooksParent = FXMLLoader.load(getClass().getResource("ownerBooks.fxml"));
        Scene ownerBooks = new Scene(ownerBooksParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(ownerBooks);
        window.show();
    }
    
    /**
     * Change scene to login screen when logout button is pressed
     */
    @FXML
    private void logout(ActionEvent event) throws IOException {
        Parent adminLoginParent = FXMLLoader.load(getClass().getResource("adminLogin.fxml"));
        Scene adminLogin = new Scene(adminLoginParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(adminLogin);
        window.show();
    }
    
    /**
     * Change scene to customer management screen when customer button is pressed
     */
    @FXML
    private void cust(ActionEvent event) throws IOException {
        Parent Owner_CustomerParent = FXMLLoader.load(getClass().getResource("Owner_Customer.fxml"));
        Scene Owner_Customer = new Scene(Owner_CustomerParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(Owner_Customer);
        window.show();
    }
}
