package bookstore.app;

import java.util.ArrayList;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 *
 * @author jason
 */
public class Book {
    public double price; 
    public String name;
    public CheckBox select;
    
    Book(String name, double price){
        this.name=name;
        this.price=price; 
        this.select = new CheckBox();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public CheckBox getBookSelection() {
        return select;
    }
 
    public void setBookSelection(CheckBox select) {
        this.select = select;
    }
}
