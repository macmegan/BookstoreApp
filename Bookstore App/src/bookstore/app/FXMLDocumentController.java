/*
 */
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

/**
 *
 * @author Megan Mac
 */
public class FXMLDocumentController implements Initializable {
    //declaring elements
    @FXML
    private Label loginError;
    @FXML
    Button login;
    @FXML
    Button adminLogin;
    @FXML
    protected TextField txtusername;
    @FXML
    protected PasswordField txtpassword;
    @FXML
    TableView tableview;
    @FXML
    protected TextField aduser;
    @FXML
    protected PasswordField adpass;
    
    
    
    
     //gets called when admin-login button is clicked
    @FXML
    private void SuccessfulAdminLogin(ActionEvent event) throws IOException {
        //Check if username and password is admin then load owner start screen
        if("admin".equals(aduser.getText()) && "admin".equals(adpass.getText())){
            Parent ownerStartParent = FXMLLoader.load(getClass().getResource("ownerStart.fxml"));
            Scene ownerStart = new Scene(ownerStartParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(ownerStart);
            window.show();
        }
        else
            loginError.setText("Login Failed");
    }
    
   
    //checking user login
   //called on userlogin screen when user login successful  
    public void successfulLogin (ActionEvent event) throws IOException {
        Customer user = new Customer(txtusername.getText(), txtpassword.getText(), 0);     
        FileWriter write = new FileWriter("src//bookstore//app//CurrentCustomer.txt", false);
        write.write(txtusername.getText() + " " + txtpassword.getText());
        write.close();
        
        if(user.login()){ //if login method returns true then label shows login success
                loginError.setText("Login Success");
           //loads customerStart by setting new scene
                Parent CustomerStartScreenParent = FXMLLoader.load(getClass().getResource("FMXL_customerStart.fxml"));
                
                Scene CustomerStartScreen = new Scene(CustomerStartScreenParent);
                
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setUserData(user);
                window.setScene(CustomerStartScreen);
                window.show();
                
            }
        else{
            // error message incase sth goes wrong
            loginError.setText("Login Failed");
        }
        } 
        
        
    
    //When sign-in as admin is clicked it loads new scene: adminlogin.fxml 
    public void adminLogin (ActionEvent event) throws IOException {
        Parent adminLoginParent = FXMLLoader.load(getClass().getResource("adminLogin.fxml"));
        Scene adminLogins = new Scene(adminLoginParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(adminLogins);
        window.show();
    }    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
