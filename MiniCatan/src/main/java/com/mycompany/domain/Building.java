
package com.mycompany.domain;

/**
 * Buildings produce resources to the players.
 */
public class Building {
    private Player player;
    private int value;
    
    public Building(Player player) {
        this.player = player;
        this.value = 1;
    }
    
    public int getValue() {
        return value;
    }

    public Player getPlayer() {
        return player;
    }
    
    /**
     * Method upgrade the level of this building by 1.
     */
    public void upgrade() {
        this.value++;
        System.out.println("Players: " + this.player.getName() + " building has upgraded to level: " + this.value);
    }
    
    /**
     * The method gives the parameter resources to the owner of this building, according to the level of the building.
     * @param resource 
     */
    public void produce(Resource resource) {
        this.player.giveResources(resource, this.value);
    }
    
}
