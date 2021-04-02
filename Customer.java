/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapp;

import java.util.ArrayList;
import java.util.Scanner;


public class Customer extends User{

private State silver;
private State gold;
private int points;
private State status;

    @Override
    public int hashCode() {
            return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
            return super.equals(obj);
    }
    

public Customer (String password, String username, int points){
        super(password, username);
        this.points=0; 
       silver = new Silver(this);
       gold = new Gold(this);
       status = silver;
    }

    public int getPoints(){
        return points;
    }
  
public double buy (String name, double price){
    return 0;
}
public double redeemAndBuy(String name, double price){  
    return 0;

}
public void setStatus(State s){
    this.status=s;
}
public State getStatus(){

return status;
}

    public State getSilver() {
        return silver;
    }

    public void setSilver(State silver) {
        this.silver = silver;
    }

    public State getGold() {
        return gold;
    }

    public void setGold(State gold) {
        this.gold = gold;
    }

}


