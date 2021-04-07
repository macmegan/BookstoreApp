package bookstore.app;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.SelectionMode;
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
public class OwnerBooksController implements Initializable {

    // declares elements of the window

    @FXML Button addBook;
    @FXML Button deleteBook;
    @FXML Button back;
    @FXML TableView<Book> bookTableView;
    @FXML TableColumn<Book,String> bookNameCol;
    @FXML TableColumn<Book,String> bookPriceCol;
    @FXML TextField enterBookName;
    @FXML TextField enterBookPrice;
    
    /**
     * Changes owner book screen back to owner start screen
     * @param event
     * @throws IOException 
     */
    public void backButton (ActionEvent event) throws IOException {
        //sets scene to the last scene: ownerstart.fxml
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
        bookNameCol.setCellValueFactory(new PropertyValueFactory<Book,String>("name"));
        bookPriceCol.setCellValueFactory(new PropertyValueFactory<Book,String>("price"));
        
        bookTableView.setItems(getBooks());
    }
    
    /**
     * Adds new book object to table
     */
    public void addBook() throws IOException {
        Book newBook = new Book(enterBookName.getText(), Double.parseDouble(enterBookPrice.getText()));
        bookTableView.getItems().add(newBook);
        BufferedWriter writer = new BufferedWriter(new FileWriter("src//bookstore//app//Books.txt", true));
        
        writer.append("\n" + enterBookName.getText() + " " + enterBookPrice.getText());
        
        writer.close();
    }
    
    /**
     * Deletes existing book object from table
     */
    public void deleteBook() throws FileNotFoundException, IOException {
        ObservableList<Book> selectedRows, allBooks;
        allBooks = bookTableView.getItems();
        
        selectedRows = bookTableView.getSelectionModel().getSelectedItems();
           
        for (Book book: selectedRows) {         
            allBooks.remove(book);
        }
        
        FileWriter writeBook = new FileWriter("src//bookstore//app/Books.txt");
        for (Book book : allBooks){
            writeBook.write(book.name + " " + book.price + System.lineSeparator()); 
            
        }
        writeBook.close();
        
    }
    
    /**
     * Returns ObservableList of Book objects
     * @return books
     */
    public ObservableList<Book> getBooks(){
        ObservableList<Book> books = FXCollections.observableArrayList();
        
        try {
            File myBook = new File("src//bookstore//app//Books.txt");
            Scanner readin = new Scanner(myBook);
            while(readin.hasNextLine()){
                String[] BookInfo = readin.nextLine().split(" ");
                books.add(new Book(BookInfo[0], Double.parseDouble(BookInfo[1])));
        } 
        } catch(FileNotFoundException e){
            System.out.println(e);
        }
        
        return books;
    }
}
