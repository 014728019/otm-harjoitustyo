
package com.mycompany.logics;

import com.mycompany.domain.Player;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class manages orders of turns on the game.
 */
public class Turn {
    private ArrayList<Player> players;
    private int moves;
    private int playersCount;
    
    public Turn(ArrayList<Player> players) {
        this.players = new ArrayList<>();
        this.moves = 0;
        this.playersCount = players.size();
        
        // Set 2 initialization round order of turns(first normal, second backward).
        players.stream().forEach(p-> {
            this.players.add(p);
        });
        
        for (int i = this.playersCount - 1; i >= 0; i--) {
            this.players.add(this.players.get(i));
        }
        
        // Set order of turns on first normal round.
        for (int i = 0; i < this.playersCount; i++) {
            this.players.add(players.get(i));
        }
        
    }
    
    /**
     * @return <i>Player</i> - player on turn
     */
    public Player getPlayer() {
        return this.players.get(0);
    }
    
    /**
     * Moves turn to the next player. Remove the first player on turn list and 
     * add it to the end of the list.
     */
    public void next() {
        if (this.moves >= this.players.size() * 2) {
            this.players.add(this.players.get(0));
        }
        
        this.players.remove(0);
        
        this.moves++;
    }
    
    /**
     * @return <i>true</i> - if 2 initialization rounds has past
     * <br>   <i>false</i> - otherwise
     */
    public boolean realTurn() {
        if (this.players.size() == this.playersCount) {
            return true;
        }
        return false;
    }
    
}
