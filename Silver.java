/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapp;

/**
 *
 * @author jason
 */
public class Silver implements State {
private Customer c;
    public Silver(Customer aThis) {
        c=aThis;
    }

    @Override
    public void status() {
        if (c.getPoints()>=1000){
            c.setStatus(c.getGold());
        }
    }
 
}
