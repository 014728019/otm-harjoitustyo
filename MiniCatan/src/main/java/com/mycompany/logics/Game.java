package com.mycompany.logics;

import com.mycompany.database.DaoResources;
import com.mycompany.domain.Building;
import com.mycompany.domain.FieldWeb;
import com.mycompany.domain.Node;
import com.mycompany.domain.NodeWeb;
import com.mycompany.domain.Player;
import com.mycompany.domain.Resource;
import com.mycompany.domain.Road;
import com.mycompany.domain.StatisticsBuilder;
import java.util.ArrayList;

/**
 * Class manages game functionality.
 */
public class Game implements DaoResources {

    private Turn turn;
    private Dices dices;
    private ArrayList<Player> players;
    private NodeWeb nodeWeb;
    private FieldWeb fieldWeb;
    private int upgradeLimit = 3;
    private int winPointsLimit;

    public Game(ArrayList<Player> players, int winPointsLimit) {
        this.players = players;
        this.turn = new Turn(players);
        this.dices = new Dices();
        this.nodeWeb = new NodeWeb();
        this.fieldWeb = new FieldWeb();
        this.winPointsLimit = winPointsLimit;
    }

    public int getWinPointsLimit() {
        return winPointsLimit;
    }
    
    /**
     * Realizes the functionality when a player presses the road button. 
     *
     * @param   road   clicked road
     */
    public void clickRoad(Road road) {
        if (road.getPlayer() == null && this.playerHasPathToRoad(road)) {
            if (this.turn.getPlayer().makeRoad()) {
                road.setPlayer(this.turn.getPlayer());
                this.turn.getPlayer().getRoads().add(road);
            }
        }
    }

    private boolean playerHasPathToRoad(Road road) {
        if (road.getNode1().getBuilding() != null && road.getNode1().getBuilding().getPlayer().equals(this.turn.getPlayer())) {
            return true;
        }
        if (road.getNode2().getBuilding() != null && road.getNode2().getBuilding().getPlayer().equals(this.turn.getPlayer())) {
            return true;
        }
        for (Road r : this.turn.getPlayer().getRoads()) {
            if (r.inTouch(road)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Realizes the functionality when a player presses the node button. 
     *
     * @param   node   clicked node
     */
    public void clickNode(Node node) {
        if (this.nodeIsFreeToBuild(node) && this.buildsOnFirst2Round()) {
            this.makeBuilding(node);
            this.turn.next();
            if (this.turn.getPlayer().getBuildings().size() == 2) {
                this.throwDice();
            }
        } else if (this.nodeIsFreeToBuild(node) && this.playerHasPathToNode(node)) {
            if (this.turn.getPlayer().makeBuilding()) {
                this.makeBuilding(node);
            }
        } else if (node.getBuilding() != null && node.getBuilding().getPlayer().equals(this.turn.getPlayer()) && node.getBuilding().getValue() < this.upgradeLimit) {
            if (this.turn.getPlayer().upgradeBuilding()) {
                node.getBuilding().upgrade();
            }
        }
    }

    private void makeBuilding(Node node) {
        Building b = new Building(this.turn.getPlayer());
        this.turn.getPlayer().getBuildings().add(b);
        node.makeBuilding(b);
    }

    private boolean nodeIsFreeToBuild(Node node) {
        if (node.getBuilding() == null && node.getNeighbours().stream()
                .map(n -> this.nodeWeb.getNode(n))
                .filter(n -> n.getBuilding() != null).count() == 0) {
            return true;
        }
        return false;
    }

    private boolean buildsOnFirst2Round() {
        if (this.turn.getPlayer().getBuildings().size() < 2) {
            return true;
        }
        return false;
    }

    private boolean playerHasPathToNode(Node node) {
        if (this.turn.getPlayer().getRoads().stream().filter(r -> r.inTouch(node)).count() >= 1) {
            return true;
        }
        return false;
    }
    
    /**
     * @return <i>true</i> - if there is 2 initialization rounds past
     * <br>   <i>false</i> - otherwise
     */
    public boolean isAfterInitRounds() {
        return this.turn.realTurn();
    }

    public Dices getDices() {
        return this.dices;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public NodeWeb getNodeWeb() {
        return this.nodeWeb;
    }

    public FieldWeb getFieldWeb() {
        return this.fieldWeb;
    }
    
    /**
     * Throw dices and collect resources from fields to players.
     */
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
    
    /**
     * Set the turn to the next player.
     */
    public void nextTurn() {
        this.turn.next();
    }
    
    /**
     * @return  <i>Player</i> - in turn
     */
    public Player getPlayerOnTurn() {
        return this.turn.getPlayer();
    }
    
    /**
     * Test if some player has enough win points to win. 
     * @return <i>true</i> - if some player have won
     * <br>   <i>false</i> - otherwise
     */
    public boolean testEnd() {
        if (this.players.stream().mapToInt(p -> p.getWinPoints()).max().getAsInt() >= this.winPointsLimit) {
            System.out.println("Game over!");
            try {
                for (Player p : this.players) {
                    STATDAO.add(new StatisticsBuilder(p, this));
                }
            } catch (Exception e) {
                System.out.println("Players statistics couldn't be saved to database.");
                System.out.println("ERROR: " + e);
            }
            return true;
        }
        return false;
    }
    
    public boolean changeResources(Resource playerGive, Resource playerWant) {
        if (this.getPlayerOnTurn().changeResources3to1(playerGive, playerWant)) {
            return true;
        }
        return false;
    }
}
