
package com.mycompany.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import javafx.scene.paint.Color;

/**
 * Player in game.
 */
public class Player implements Comparable<Player> {
    private String name;
    private HashMap<Resource, Integer> resources;
    private Color color;
    private ArrayList<Building> buildings;
    private ArrayList<Road> roads;
    
    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.resources = new HashMap<>();
        this.buildings = new ArrayList<>();
        this.roads = new ArrayList<>();
        
        this.resources.put(Resource.Savi, 0);
        this.resources.put(Resource.Vilja, 0);
        this.resources.put(Resource.Lammas, 0);
        this.resources.put(Resource.Kivi, 0);
        this.resources.put(Resource.Puu, 0);
    }
    
    /**
     * @return <i>int</i> - sum from building values
     */
    public int getWinPoints() {
        return this.buildings.stream().mapToInt(m -> m.getValue()).sum();
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public ArrayList<Road> getRoads() {
        return roads;
    }

    public HashMap<Resource, Integer> getResources() {
        return resources;
    }
    
    /**
     * @return <i>true</i> - if player has resources to make road(and decrease 
     * players resources)
     * <br>   <i>false</i> - otherwise
     */
    public boolean makeRoad() {
        if (this.resources.get(Resource.Savi) >= 1 && this.resources.get(Resource.Puu) >= 1) {
            int clays = this.resources.get(Resource.Savi);
            int wood = this.resources.get(Resource.Puu);
            clays--;
            wood--;
            this.resources.put(Resource.Savi, clays);
            this.resources.put(Resource.Puu, wood);
            return true;
        }
        return false;
    }
    
    /**
     * @return <i>true</i> - if player has resources to make building(and decrease 
     * players resources)
     * <br>   <i>false</i> - otherwise
     */
    public boolean makeBuilding() {
        if (this.resources.get(Resource.Savi) >= 1 && this.resources.get(Resource.Puu) >= 1
                && this.resources.get(Resource.Lammas) >= 1 && this.resources.get(Resource.Vilja) >= 1) {
            int clays = this.resources.get(Resource.Savi);
            int wood = this.resources.get(Resource.Puu);
            int corp = this.resources.get(Resource.Vilja);
            int sheep = this.resources.get(Resource.Lammas);
            clays--;
            wood--;
            corp--;
            sheep--;
            this.resources.put(Resource.Savi, clays);
            this.resources.put(Resource.Puu, wood);
            this.resources.put(Resource.Vilja, corp);
            this.resources.put(Resource.Lammas, sheep);
            return true;
        }
        return false;
    }
    
    /**
     * @return <i>true</i> - if player has resources to upgrade building(and 
     * decrease players resources)
     * <br>   <i>false</i> - otherwise
     */
    public boolean upgradeBuilding() {
        if (this.resources.get(Resource.Kivi) >= 3 && this.resources.get(Resource.Vilja) >= 2) {
            int stone = this.resources.get(Resource.Kivi);
            int corp = this.resources.get(Resource.Vilja);
            stone = stone - 3;
            corp = corp - 2;
            this.resources.put(Resource.Vilja, corp);
            this.resources.put(Resource.Kivi, stone);
            return true;
        }
        return false;
    }
    
    /**
     * Gives a number of resources to the player.
     * @param resource
     * @param number 
     */
    public void giveResources(Resource resource, int number) {
        this.resources.replace(resource, this.resources.get(resource) + number);
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
    
    /**
     * @return <i>String</i> - player name and amount of resources
     */
    public String getStatus() {
        return this.name + 
                "\n\tSavi: " + this.resources.get(Resource.Savi) +
                "\n\tPuu: " + this.resources.get(Resource.Puu) +
                "\n\tLammas: " + this.resources.get(Resource.Lammas) +
                "\n\tVilja: " + this.resources.get(Resource.Vilja) +
                "\n\tKivi: " + this.resources.get(Resource.Kivi);
    }
    
    /**
     * @param obj
     * @return <i>true</i> - if players have same name
     * <br>   <i>false</i> - otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    /**
     * For sorting list of players to unnatural order(player with highest count 
     * of win points on top).
     * @param player
     */
    @Override
    public int compareTo(Player player) {
        return player.getWinPoints() - this.getWinPoints();
    }
}
