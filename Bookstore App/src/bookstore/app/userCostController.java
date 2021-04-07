package bookstore.app;

/**
 *
 * @author AMR
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Megan Mac
 */
public class userCostController implements Initializable {
    // FXML DECLARATIONS
    // Button for user interaction
    @FXML Button logout;
    
    // Labels to display customer information 
    @FXML private Label pointstxt;
    @FXML private Label statustxt;
    @FXML private Label totalcost;
    
    /**
     * Takes user back to login screen when the logout button is pushed; 
    this loads up the scene of the UserLogin window
     */
    public void logout (ActionEvent event) throws IOException {
        Parent logoutParent = FXMLLoader.load(getClass().getResource("userLogin.fxml"));
        Scene logout = new Scene(logoutParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(logout);
        window.show();
    }    
    
    /**
     * Initializes the controller class.
     */

    //since the customer.txt and book.txt is already loaded as new after the buy and redeeemBuy is clicked from the CustomerStartController, here we load the customer.txt
    //and books.txt again and then we compare the points again of the updated info and then set texts to the labels: pointstxt, statustxt and totalcost
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
        
        pointstxt.setText(pointsnew);
        if(Integer.parseInt(pointsnew) > 1000)
            statustxt.setText("Gold");
        else
            statustxt.setText("Silver");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FMXL_customerStartController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            File totalfilecost = new File("src//bookstore//app//TotalCost.txt");
            Scanner s = new Scanner(totalfilecost);
            totalcost.setText(s.nextLine());
        
        } catch(FileNotFoundException e){
            System.out.println(e);
        }
    }     
}
