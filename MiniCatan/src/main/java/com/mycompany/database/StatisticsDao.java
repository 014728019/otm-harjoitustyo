package com.mycompany.database;

import com.mycompany.domain.Player;
import com.mycompany.domain.StatisticsBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StatisticsDao implements Dao<StatisticsBuilder, String> {

    private Database database;

    public StatisticsDao(Database db) {
        this.database = db;
    }

    @Override
    public StatisticsBuilder findOne(String key) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StatisticsBuilder> findAll() throws Exception {
        ArrayList<StatisticsBuilder> builder = new ArrayList<>();
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Player");
        
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            String name = rs.getString("name");
            
            StatisticsBuilder o = new StatisticsBuilder(name);
            
            PreparedStatement stmt1 = connection.prepareStatement("SELECT * FROM GameTable WHERE player = ?");
            stmt1.setString(1, name);
            
            ResultSet rs1 = stmt1.executeQuery();
            
            while (rs1.next()) {
                o.add(rs1.getInt(2), rs1.getInt(3), rs1.getInt(4), rs1.getInt(5));
            }
            
            builder.add(o);
        }
        
        return builder;
    }

    @Override
    public void delete(String key) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(StatisticsBuilder o) throws Exception {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO GameTable (player, playersOnGame, placement, winPointLimit, winPoints) "
                + "VALUES (?, ?, ?, ?, ?);");
        stmt.setString(1, o.getName());
        stmt.setInt(2, o.getPlayersOnGame());
        stmt.setInt(3, o.getPlacement());
        stmt.setInt(4, o.getWinPointLimit());
        stmt.setInt(5, o.getWinPoint());

        stmt.executeUpdate();
        stmt.close();
        connection.close();
        
        System.out.println("Adding game data secceeded from player: "+o.getName());
    }

}
