package com.mycompany.domain;

import java.util.Objects;

/**
 * Road connect 2 nodes on the game and could have owner.
 */
public class Road {
    private Player player;
    private Node node1;
    private Node node2;

    public Road(Player player, Node node1, Node node2) {
        this.player = player;
        this.node1 = node1;
        this.node2 = node2;
    }

    public Node getNode1() {
        return node1;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Node getNode2() {
        return node2;
    }
    
    /**
     * @return <i>Location</i> - location of 1st node
     */
    public Location getLocation1() {
        return this.node1.getLocation();
    }
    
    /**
     * @return <i>Location</i> - location of 2nd node
     */
    public Location getLocation2() {
        return this.node2.getLocation();
    }

    public Player getPlayer() {
        return player;
    }
    
    /**
     * @param road
     * @return <i>true</i> - if roads have common node and node doesn't have building 
     * or building is owned by roads owner
     * <br>    <i>false</i> - otherwise
     */
    public boolean inTouch(Road road) {
        if (this.node1.equals(road.getNode1()) || this.node2.equals(road.getNode1())) {
            if (road.getNode1().getBuilding() == null) {
                return true;
            } else if (road.getNode1().getBuilding().getPlayer().equals(this.player)) {
                return true;
            }
        }
        if (this.node1.equals(road.getNode2()) || this.node2.equals(road.getNode2())) {
            if (road.getNode2().getBuilding() == null) {
                return true;
            } else if (road.getNode2().getBuilding().getPlayer().equals(this.player)) {
                return true;
            }
        }
        return false;
    }
    /**
     * 
     * @param node
     * @return <i>true</i> - if one of the two endpoints is this node
     * <br>    <i>false</i> - otherwise
     */
    public boolean inTouch(Node node) {
        if (this.node1.getId().equals(node.getId()) || this.node2.getId().equals(node.getId())) {
            return true;
        }
        return false;
    }
    
    /**
     * @param obj
     * @return <i>true</i> - if both roads have same endpoints
     * <br>   <i>false</i> - otherwise
     */
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
