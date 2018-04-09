
package logic;

import java.util.Random;

public class Dices {
    private int throwed;
    
    public int throwDices() {
        this.throwed = new Random().nextInt(6) + new Random().nextInt(6) + 2;
        System.out.println("Heitettiin: "+this.throwed);
        return this.throwed;
    }

    public int getThrowed() {
        return throwed;
    }
    
}
