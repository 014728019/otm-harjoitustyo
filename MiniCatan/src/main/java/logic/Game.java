package logic;

import domain.Building;
import domain.FieldWeb;
import domain.Node;
import domain.NodeWeb;
import domain.Player;
import domain.Road;
import graphic.GameView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        Road newRoad = new Road(turn.getPlayer(), road.getNode1(), road.getNode2());
        if (!this.roads.contains(road)) {
            if ((road.getNode1().getBuilding() != null && road.getNode1().getBuilding().getPlayer().equals(turn.getPlayer()))
                    || (road.getNode2().getBuilding() != null && road.getNode2().getBuilding().getPlayer().equals(turn.getPlayer()))) {
                if (turn.getPlayer().makeRoad()) {
                    this.roads.add(newRoad);
                    return newRoad;
                }

            }
        }

        if (!this.roads.contains(road)) {
            for (Road r : this.roads) {
                if (r.getPlayer().equals(turn.getPlayer())) {
                    if (r.getNode1().equals(road.getNode1()) && road.getNode1().getBuilding() == null) {
                        if (turn.getPlayer().makeRoad()) {
                            this.roads.add(newRoad);
                            return newRoad;
                        }
                        break;
                    }
                    if (r.getNode2().equals(road.getNode2()) && road.getNode2().getBuilding() == null) {
                        if (turn.getPlayer().makeRoad()) {
                            this.roads.add(newRoad);
                            return newRoad;
                        }
                        break;
                    }
                    if (r.getNode2().equals(road.getNode1()) && road.getNode1().getBuilding() == null) {
                        if (turn.getPlayer().makeRoad()) {
                            this.roads.add(newRoad);
                            return newRoad;
                        }
                        break;
                    }
                    if (r.getNode1().equals(road.getNode2()) && road.getNode2().getBuilding() == null) {
                        if (turn.getPlayer().makeRoad()) {
                            this.roads.add(newRoad);
                            return newRoad;
                        }
                        break;
                    }
                }
            }
        }
        return null;
    }

    public Building clickNode(Node node) {
        if (node.getBuilding() == null && node.getNeighbours().stream()
                .map(n -> this.nodeWeb.getNode(n))
                .filter(n -> n.getBuilding() != null).count() == 0) {
            if (this.buildings.stream().filter(b -> b.getPlayer().equals(turn.getPlayer())).count() < 2) {
                Building b = new Building(turn.getPlayer());
                this.buildings.add(b);
                node.makeBuilding(b);
                turn.next();
                if (this.buildings.size() == this.players.size() * 2) {
                    this.throwDice();
                }
                return b;
            } else if (this.roads.stream()
                    .filter(r -> r.getPlayer().equals(turn.getPlayer()))
                    .filter(r -> r.getLocation1().equals(node.getLocation()) || r.getLocation2().equals(node.getLocation())).count()
                    >= 1) {
                if (turn.getPlayer().makeBuilding()) {
                    Building b = new Building(turn.getPlayer());
                    this.buildings.add(b);
                    node.makeBuilding(b);
                    return b;
                }
            }
        } else if (node.getBuilding() != null && node.getBuilding().getPlayer().equals(turn.getPlayer()) && node.getBuilding().getValue() < upgradeLimit) {
            if (turn.getPlayer().upgradeBuilding()) {
                node.getBuilding().upgrade();
                return node.getBuilding();
            }
        }
        return null;
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
