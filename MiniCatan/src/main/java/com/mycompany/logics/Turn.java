
package com.mycompany.logics;

import com.mycompany.domain.Player;
import java.util.ArrayList;
import java.util.Random;

public class Turn {
    private ArrayList<Player> players;
    private int moves;
    private int playersCount;
    
    public Turn(ArrayList<Player> players) {
        this.players = new ArrayList<>();
        this.moves = 0;
        this.playersCount = players.size();
        
        players.stream().forEach(p-> {
            this.players.add(p);
        });
        
        for (int i = this.playersCount - 1; i >= 0; i--) {
            this.players.add(this.players.get(i));
        }
        
        for (int i = 0; i < this.playersCount; i++) {
            this.players.add(players.get(i));
        }
        
    }
    
    public Player getPlayer() {
        return this.players.get(0);
    }
    
    public void next() {
        if (moves >= this.players.size() * 2) {
            this.players.add(this.players.get(0));
        }
        
        this.players.remove(0);
        
        this.moves++;
    }

    public boolean realTurn() {
        if (this.players.size() == this.playersCount) {
            return true;
        }
        return false;
    }
    
}
