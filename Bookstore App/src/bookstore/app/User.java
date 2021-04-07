package bookstore.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author jason
 */
public abstract class User {
    // Variable declaration
    public ArrayList<Book> bookList = new ArrayList <Book>();
    protected ArrayList<Customer> customersList = new ArrayList<Customer>();
    private String password;
    private String username;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }
      
    public User(String password, String username){
        this.password=password;
        this.username=username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean login(){
        ArrayList<String> custlist = new ArrayList<>();    
    
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
        
        for(String i : custlist){
            String[] custlist2 = i.split(" ");
            if(username.equals(custlist2[0]) && password.equals(custlist2[1])){
                return true;
            }
        }
        return false;
    }
    
    public void logout(){}

}
    

