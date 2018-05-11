
package com.mycompany.logics;

import java.util.Random;

/**
 * Class manages dices during the game.
 */
public class Dices {
    private int throwed;
    
    /**
     * Generate 2 integer (1-6) and return sum of them.
     * @return <i>int</i> - sum from 2 cubic dice values
     */
    public int throwDices() {
        this.throwed = new Random().nextInt(6) + new Random().nextInt(6) + 2;
        return this.throwed;
    }

    public int getThrowed() {
        return throwed;
    }
    
}
