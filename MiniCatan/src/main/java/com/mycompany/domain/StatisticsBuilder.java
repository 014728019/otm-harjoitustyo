
package com.mycompany.domain;

import com.mycompany.logics.Game;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class StatisticsBuilder {
    private String name;
    private ArrayList<Integer> placements;
    private ArrayList<Integer> winPointLimits;
    private ArrayList<Integer> winPoints;
    private ArrayList<Integer> playersOnGame;
    private int placement;
    private int winPointLimit;
    private int winPoint;
    private int playersOnThisGame;
    
    public StatisticsBuilder(String name) {
        this.name = name;
        this.placements = new ArrayList<>();
        this.winPointLimits = new ArrayList<>();
        this.playersOnGame = new ArrayList<>();
        this.winPoints = new ArrayList<>();
    }
    
    public StatisticsBuilder(Player player, Game game) {
        this.name = player.getName();
        this.winPointLimit = game.getWinPointsLimit();
        this.playersOnThisGame = game.getPlayers().size();
        this.winPoint = player.getWinPoints();
        
        ArrayList<Player> playersSorted = game.getPlayers().stream().sorted().collect(Collectors.toCollection(ArrayList::new));
        
        for (int i = 0; i < playersSorted.size(); i++) {
            if (playersSorted.get(i).equals(player)) {
                this.placement = i+1;
                break;
            }
        }
    }
    
    public void add(int playersOnGame, int placement, int winPointLimit, int winPoints) {
        this.placements.add(placement);
        this.playersOnGame.add(playersOnGame);
        this.winPointLimits.add(winPointLimit);
        this.winPoints.add(winPoints);
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getGamesCount() {
        return this.placements.size();
    }
    
    public double getWinRatio() {
        return (double) this.placements.stream().filter(m -> m == 1).count() / this.placements.size();
    }
    
    public double getAverageWinPointsFromLimit() {
        double avgLimit = this.winPointLimits.stream().mapToDouble(m -> (double) m).average().orElse(0);
        double avgPoints = this.winPoints.stream().mapToDouble(m -> (double) m).average().orElse(0);
        return avgPoints/avgLimit;
    }

    public int getPlacement() {
        return placement;
    }

    public int getWinPointLimit() {
        return winPointLimit;
    }

    public int getWinPoint() {
        return winPoint;
    }

    public int getPlayersOnGame() {
        return playersOnThisGame;
    }
    
}
