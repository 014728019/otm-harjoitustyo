
package com.mycompany.domain;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String id;
    private Location location;
    private ArrayList<String> neighbours;
    private Building building;
    
    public Node(String id, Location location, List<String> neighbours) {
        this.id = id;
        this.location = location;
        this.neighbours = new ArrayList<>();
        this.building = null;
        
        neighbours.stream().forEach(n-> {
            this.neighbours.add(n);
        });
    }
    
    public ArrayList<String> getNeighbours() {
        return this.neighbours;
    }
    
    /**
     * The method calls the building on this node if not null and tells it to produce this param resource.
     * @param resource 
     */
    public void produce(Resource resource) {
        if (this.building != null) {
            this.building.produce(resource);
        }
    }
    
    /**
     * Set the building of this node to the param building if this node's building is null.
     * @param building 
     */
    public void makeBuilding(Building building) {
        if (this.building == null) {
            this.building = building;
        }
    }
    
    /**
     * Tells to the building to upgrade if its not null.
     */
    public void upgradeBuilding() {
        if (this.building != null) {
            this.building.upgrade();
        }
    }

    public Location getLocation() {
        return location;
    }

    public String getId() {
        return id;
    }

    public Building getBuilding() {
        return building;
    }
    
    
}
