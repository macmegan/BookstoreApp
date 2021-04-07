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
 * @author LENOVO
 */
public class Owner_CustomerController implements Initializable {

    @FXML private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     * Changes scene to owner start screen when back button is pushed
     */
    @FXML
    private void back_button(ActionEvent event) throws IOException {
        // Create and load scene
        Parent ownerStartParent = FXMLLoader.load(getClass().getResource("ownerStart.fxml"));
        Scene ownerStart = new Scene(ownerStartParent);
        
        // Retrieve stage info 
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        // Change scene
        window.setScene(ownerStart);
        window.show();
    }
    
}
