package bookstore.app;

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
