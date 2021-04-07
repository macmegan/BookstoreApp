package bookstore.app;

import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends User {

    protected State silver;
    protected State gold;
    protected int points;
    protected State status;
    protected double totalCost = 0;

    @Override
    public int hashCode() {
            return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
            return super.equals(obj);
    }

    public Customer (String username, String password, int points){
        super(password, username);
        this.points = points;
        silver = new Silver(this);
        gold = new Gold(this);
        
        if (points >= 1000) {
            status = gold;
        } 
        else {
            status = silver;
        }
    }

    public int getPoints(){
        return points;
    }
  
    public double buy(Book b) {
        totalCost += b.getPrice();
        return totalCost;
    }

    private void redeemPoints(int pointsToRedeem) {
        points -= pointsToRedeem;
        silver.status();
    }
    
    public double redeemAndBuy(Book b){  
        int pointsRedeem = ((int) b.getPrice()) * 100;
            if (points >= pointsRedeem) {
                redeemPoints(pointsRedeem);
                totalCost += b.getPrice() - pointsRedeem / 100;
            } 
            else {
                int dollarsToRedeem = points / 100;
                redeemPoints(dollarsToRedeem * 100);
                totalCost += b.getPrice() - dollarsToRedeem;
            }
        return totalCost;
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


