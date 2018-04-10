/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import database.Database;
import database.PlayerDao;
import domain.Player;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

/**
 *
 * @author Jake
 */
public class NewPlayerView implements View {

    @Override
    public void show(Stage stage) {
        try {
            PlayerDao playerDao = new PlayerDao(new Database("jdbc:sqlite:MiniCatanDatabase.db"));
            Dialog<List<Player>> dialog = new Dialog<>();
            VBox view = new VBox();

            Label head = new Label("Luo uusi pelaaja\n");
            head.setFont(Font.font(16));
            Label name = new Label("Nimi:");
            TextField input = new TextField();
            Label note = new Label("Huom. nimen täytyy olla uniikki ja sallittu merkkimäärä on 1-50");
            Button addPlayer = new Button("Lisää pelaaja");

            addPlayer.setOnAction((event) -> {
                if (input.getText().length() >= 1 && input.getText().length() <= 50) {
                    try {
                        Player player = new Player(input.getText(), null);
                        if (playerDao.findAll().contains(player)) {
                            throw new Exception("Tietokannasta löytyi pelaaja entuudestaan kyseisellä nimellä! Valitse toinen nimi!");
                        }
                        playerDao.add(player);
                        System.out.println("New player added: " + player.getName());
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle(null);
                        alert.setHeaderText(null);
                        alert.setContentText("Uusi pelaaja lisätty onnistuneesti nimellä: " + player.getName());
                        alert.showAndWait();
                    } catch (Exception e) {
                        System.out.println("ERROR: " + e);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("ERROR");
                        alert.setHeaderText(null);
                        alert.setContentText(e.getMessage());
                        alert.showAndWait();
                    }
                    input.clear();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Nimessä täytyy olla 1-50 merkkiä!");
                    alert.showAndWait();
                }
            });
            
            ButtonType cancelButton = new ButtonType("Takaisin", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(cancelButton);

            view.getChildren().addAll(head, name, input, note, addPlayer);
            dialog.getDialogPane().setContent(view);
            dialog.showAndWait();
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

}
