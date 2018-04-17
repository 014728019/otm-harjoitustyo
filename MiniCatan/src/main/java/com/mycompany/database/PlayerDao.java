package com.mycompany.database;

import com.mycompany.domain.Player;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.management.BadAttributeValueExpException;
import javax.naming.NamingException;

public class PlayerDao implements Dao<Player, Integer> {

    private Database database;

    public PlayerDao(Database db) {
        this.database = db;
    }

    @Override
    public Player findOne(Integer key) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Player> findAll() throws Exception {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Player");

        ResultSet rs = stmt.executeQuery();
        List<Player> players = new ArrayList<>();
        
        while (rs.next()) {
            String name = rs.getString("name");

            players.add(new Player(name, null));
        }

        rs.close();
        stmt.close();
        connection.close();

        return players;
    }

    @Override
    public void delete(Integer key) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Player o) throws Exception {
        if (!this.findAll().contains(o)) {
            Connection connection = database.getConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Player (name) "
                    + "VALUES (?);");
            stmt.setString(1, o.getName());

            stmt.executeUpdate();
            stmt.close();
            connection.close();
            
            System.out.println("Uusi pelaaja lisätty: " + o.getName());
        } else {
            System.out.println("Uutta pelaajaa ei voitu lisätä, koska nimi on jo varattu: " + o.getName());
            throw new Exception("Uutta pelaajaa ei voitu lisätä, koska nimi on jo varattu: " + o.getName());
        }
    }

}
