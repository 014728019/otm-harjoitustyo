
package domain;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private Resource resource;
    private int value;
    private Location location;
    private boolean disabled;
    private ArrayList<String> nodes;
    
    public Field(Resource resource, int value, Location location, ArrayList<String> nodes) {
        this.location = location;
        this.resource = resource;
        this.value = value;
        this.disabled = false;
        this.nodes = nodes;
    }
    
    public void unDisable() {
        this.disabled = false;
    }
    
    public void disable() {
        this.disabled = true;
    }

    public Resource getResource() {
        return resource;
    }

    public int getValue() {
        return value;
    }

    public Location getLocation() {
        return location;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setLocationAndNode(Location location, List<String> nodes) {
        this.location = location;
        this.nodes = new ArrayList<>();
        
        nodes.stream().forEach(n->{
            this.nodes.add(n);
        });
    }

    public ArrayList<String> getNodes() {
        return nodes;
    }
    
}
