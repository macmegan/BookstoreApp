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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Megan Mac
 */
public class ownerStartController implements Initializable {

    @FXML
    Button books;
    @FXML
    Button customers;
    @FXML
    Button logout;
    
    public void successfulLogin (ActionEvent event) throws IOException {
        Parent ownerStartParent = FXMLLoader.load(getClass().getResource("ownerStart.fxml"));
        Scene ownerStart = new Scene(ownerStartParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(ownerStart);
        window.show();
    }
    
    public void logout (ActionEvent event) throws IOException {
        Parent logoutParent = FXMLLoader.load(getClass().getResource("userLogin.fxml"));
        Scene logout = new Scene(logoutParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(logout);
        window.show();
    }
    
    public void manageBooks (ActionEvent event) throws IOException {
        Parent manageBooksParent = FXMLLoader.load(getClass().getResource("ownerBooks.fxml"));
        Scene manageBooks = new Scene(manageBooksParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(manageBooks);
        window.show();
    }
    
    public void manageCustomers (ActionEvent event) throws IOException {
        Parent manageCustomersParent = FXMLLoader.load(getClass().getResource("ownerCustomers.fxml"));
        Scene manageCust = new Scene(manageCustomersParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(manageCust);
        window.show();
    }    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
