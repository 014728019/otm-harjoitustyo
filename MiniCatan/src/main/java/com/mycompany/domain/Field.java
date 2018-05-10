
package com.mycompany.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Resource field which produce specific resource when its value is throwed.
 */
public class Field {
    private Resource resource;
    private int value;
    private Location location;
    private ArrayList<String> nodes;
    
    public Field(Resource resource, int value, Location location, ArrayList<String> nodes) {
        this.location = location;
        this.resource = resource;
        this.value = value;
        this.nodes = nodes;
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

    public void setLocationAndNodes(Location location, List<String> nodes) {
        this.location = location;
        this.nodes = new ArrayList<>();
        
        nodes.stream().forEach(n-> {
            this.nodes.add(n);
        });
    }

    public ArrayList<String> getNodes() {
        return nodes;
    }
    
}
