
package com.mycompany.database;

public interface DaoResources {
    Database DATABASE = new Database("jdbc:sqlite:MiniCatanDatabase.db");
    Dao PLAYERDAO = new PlayerDao(DATABASE);
    Dao STATDAO = new StatisticsDao(DATABASE);
    
}
