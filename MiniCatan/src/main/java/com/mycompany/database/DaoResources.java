
package com.mycompany.database;

public interface DaoResources {
    Database DATABASE = new Database("jdbc:sqlite:MiniCatanDatabase.db");
    PlayerDao PLAYERDAO = new PlayerDao(DATABASE);
    StatisticsDao STATDAO = new StatisticsDao(DATABASE);
    
}
