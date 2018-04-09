
package domain;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String id;
    private Location location;
    private ArrayList<String> neighbours;
    private Building building;
    
    public Node (String id, Location location, List<String> neighbours) {
        this.id = id;
        this.location = location;
        this.neighbours = new ArrayList<>();
        this.building = null;
        
        neighbours.stream().forEach(n->{
            this.neighbours.add(n);
        });
    }
    
    public ArrayList<String> getNeighbours() {
        return this.neighbours;
    }
    
    public void produce(Resource resource) {
        if (this.building != null) {
            this.building.produce(resource);
        }
    }
    
    public void makeBuilding(Building building) {
        if (this.building == null) {
            this.building = building;
        }
    }
    
    public void upgradeBuilding() {
        this.building.upgrade();
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
