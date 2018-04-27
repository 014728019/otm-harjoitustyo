
package com.mycompany.database;

public interface DaoResources {
    Database database = new Database("jdbc:sqlite:MiniCatanDatabase.db");
    PlayerDao playerDao = new PlayerDao(database);
    StatisticsDao statDao = new StatisticsDao(database);
    
}
