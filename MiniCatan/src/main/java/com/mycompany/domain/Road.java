
package com.mycompany.domain;

import java.util.Objects;

public class Road {
    private Player player;
    private Node node1;
    private Node node2;
    
    public Road(Player player, Node node1, Node node2) {
        this.player = player;
        this.node1 = node1;
        this.node2 = node2;
    }
    
    public boolean sameAs(Node node1, Node node2) {
        if (this.node1.getLocation().equals(node1.getLocation()) && this.node2.getLocation().equals(node2.getLocation())) {
            return true;
        }
        if (this.node1.getLocation().equals(node2.getLocation()) && this.node2.getLocation().equals(node1.getLocation())) {
            return true;
        }
        return false;
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }
    
    public Location getLocation1() {
        return this.node1.getLocation();
    }
    
    public Location getLocation2() {
        return this.node2.getLocation();
    }

    public Player getPlayer() {
        return player;
    }
    
    public boolean inTouch(Road r) {
        if (this.node1.equals(r.getNode1()) || this.node2.equals(r.getNode1()) 
                || this.node1.equals(r.getNode2()) || this.node2.equals(r.getNode2())) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.node1);
        hash = 29 * hash + Objects.hashCode(this.node2);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        Road road = (Road) obj;
        
        if (this.node1.getLocation().equals(road.getNode1().getLocation()) && this.node2.getLocation().equals(road.getNode2().getLocation())) {
            return true;
        }
        if (this.node1.getLocation().equals(road.getNode2().getLocation()) && this.node2.getLocation().equals(road.getNode1().getLocation())) {
            return true;
        }
        return false;
    }
    
}
