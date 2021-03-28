/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapp;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author jason
 */
public abstract class User {
    private String password;
    private String username;
    
    
    public void User(String password, String username, int points, String book){
        this.password=password;
        this.username=username;
        booklist= new ArrayList<Booklist>();
    }
    public abstract String getPassword();
    public abstract String getUsernmae();
    public abstract void login ();
    public abstract void logout ();
    
}
