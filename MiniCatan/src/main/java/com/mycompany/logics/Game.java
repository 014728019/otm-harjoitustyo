package com.mycompany.logics;

import com.mycompany.domain.Building;
import com.mycompany.domain.FieldWeb;
import com.mycompany.domain.Node;
import com.mycompany.domain.NodeWeb;
import com.mycompany.domain.Player;
import com.mycompany.domain.Road;
import com.mycompany.gui.GameView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Game {

    private Turn turn;
    private Dices dices;
    private ArrayList<Player> players;
    private ArrayList<Road> roads;
    private ArrayList<Building> buildings;
    private NodeWeb nodeWeb;
    private FieldWeb fieldWeb;
    private int upgradeLimit = 3;
    private int winPointsLimit;
    private boolean ended = false;

    public Game(ArrayList<Player> players, int winPointsLimit) {
        this.players = players;
        this.turn = new Turn(players);
        this.dices = new Dices();
        this.roads = new ArrayList<>();
        this.buildings = new ArrayList<>();
        this.nodeWeb = new NodeWeb();
        this.fieldWeb = new FieldWeb();
        this.winPointsLimit = winPointsLimit;
    }

    public Road clickRoad(Road road) {
        if (!this.roads.contains(road) && this.playerHasPathToRoad(road)) {
            if (turn.getPlayer().makeRoad()) {
                Road newRoad = new Road(turn.getPlayer(), road.getNode1(), road.getNode2());
                this.roads.add(newRoad);
                return newRoad;
            }
        }
        return null;
    }

    public boolean playerHasPathToRoad(Road road) {
        if (road.getNode1().getBuilding() != null && road.getNode1().getBuilding().getPlayer().equals(turn.getPlayer())) {
            return true;
        }
        if (road.getNode2().getBuilding() != null && road.getNode2().getBuilding().getPlayer().equals(turn.getPlayer())) {
            return true;
        }
        for (Road r : this.roads) {
            if (r.getPlayer().equals(turn.getPlayer()) && r.inTouch(road)) {
                return true;
            }
        }
        return false;
    }

    public Building clickNode(Node node) {
        if (this.nodeIsFreeToBuild(node) && this.buildsOnFirst2Round()) {
            Building b = this.makeBuilding(node);
            turn.next();
            if (this.buildings.size() == this.players.size() * 2) {
                this.throwDice();
            }
            return b;
        } else if (this.nodeIsFreeToBuild(node) && this.playerHasPathToNode(node)) {
            if (turn.getPlayer().makeBuilding()) {
                return this.makeBuilding(node);
            }
        } else if (node.getBuilding() != null && node.getBuilding().getPlayer().equals(turn.getPlayer()) && node.getBuilding().getValue() < upgradeLimit) {
            if (turn.getPlayer().upgradeBuilding()) {
                node.getBuilding().upgrade();
                return node.getBuilding();
            }
        }
        return null;
    }

    public Building makeBuilding(Node node) {
        Building b = new Building(turn.getPlayer());
        this.buildings.add(b);
        node.makeBuilding(b);
        return b;
    }

    public boolean nodeIsFreeToBuild(Node node) {
        if (node.getBuilding() == null && node.getNeighbours().stream()
                .map(n -> this.nodeWeb.getNode(n))
                .filter(n -> n.getBuilding() != null).count() == 0) {
            return true;
        }
        return false;
    }

    public boolean buildsOnFirst2Round() {
        if (this.buildings.stream().filter(b -> b.getPlayer().equals(turn.getPlayer())).count() < 2) {
            return true;
        }
        return false;
    }

    public boolean playerHasPathToNode(Node node) {
        if (this.roads.stream()
                .filter(r -> r.getPlayer().equals(turn.getPlayer()))
                .filter(r -> r.inTouch(node)).count() >= 1) {
            return true;
        }
        return false;
    }

    public Turn getTurn() {
        return turn;
    }

    public Dices getDices() {
        return dices;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public boolean isEnded() {
        return ended;
    }

    public ArrayList<Road> getRoads() {
        return roads;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public NodeWeb getNodeWeb() {
        return nodeWeb;
    }

    public FieldWeb getFieldWeb() {
        return fieldWeb;
    }

    public void throwDice() {
        this.dices.throwDices();

        this.getFieldWeb().getFields().stream()
                .filter(f -> f.getValue() == this.dices.getThrowed())
                .forEach(f -> {
                    f.getNodes().forEach(n -> {
                        this.nodeWeb.getNode(n).produce(f.getResource());
                    });
                });
    }

    public void nextTurn() {
        this.turn.next();
        this.testEnd();
    }

    public Player getPlayerOnTurn() {
        return this.turn.getPlayer();
    }

    public void calcWinPoints() {
        this.players.stream().forEach(p -> {
            int points = this.buildings.stream().filter(b -> b.getPlayer().equals(p)).mapToInt(b -> b.getValue()).sum();
            p.setWinPoints(points);
        });
    }

    private void testEnd() {
        this.calcWinPoints();
        if (this.players.stream().mapToInt(p -> p.getWinPoints()).max().getAsInt() >= this.winPointsLimit) {
            this.ended = true;
            System.out.println("Game over!");
        }
    }

}
