/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Jake
 */
public class MenuView implements View{

    @Override
    public void show(Stage stage) {
        VBox buttons = new VBox();

        Button newGame = new Button("Uusi peli");
        Button statistics = new Button("Tilastoja");
        Button newPlayer = new Button("Luo uusi pelaaja");
        Button showInfo = new Button("Info");

        newGame.setPrefSize(200, 50);
        statistics.setPrefSize(200, 50);
        newPlayer.setPrefSize(200, 50);
        showInfo.setPrefSize(200, 50);

        newGame.setOnAction((event) -> {
            try {
                GameView game = new GameView();
                game.show(stage);
            } catch (Exception e) {
                System.out.println("ERROR: " + e);
            }
        });

        newPlayer.setOnAction((event) -> {
            NewPlayerView newPlayerView = new NewPlayerView();
            newPlayerView.show(stage);
        });

        showInfo.setOnAction((event) -> {
            InfoView info = new InfoView();
            info.show();
        });

        buttons.getChildren().addAll(showInfo, statistics, newPlayer, newGame);
        
        Scene scene = new Scene(buttons);
        stage.setScene(scene);
        stage.centerOnScreen();
    }
    
}
