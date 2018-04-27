package com.mycompany.gui;

import com.mycompany.database.DaoResources;
import com.mycompany.database.Database;
import com.mycompany.database.StatisticsDao;
import com.mycompany.domain.Player;
import com.mycompany.domain.StatisticsBuilder;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class StatisticsView implements View, DaoResources {

    @Override
    public void show(Stage stage) {
        Dialog<List<Player>> dialog = new Dialog<>();
        VBox view = new VBox();
        dialog.getDialogPane().setContent(view);

        Label header = new Label("Tilastoja:");
        header.setFont(Font.font(18));

        TableView<StatisticsBuilder> table = new TableView<>();
        
        TableColumn name = new TableColumn("Nimi");
        name.setMinWidth(150);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn playedGames = new TableColumn("Pelatut pelit");
        playedGames.setMinWidth(100);
        playedGames.setCellValueFactory(new PropertyValueFactory<>("gamesCount"));
        
        TableColumn winRatio = new TableColumn("Voitto osuus(%)");
        winRatio.setMinWidth(120);
        winRatio.setCellValueFactory(new PropertyValueFactory<>("winRatio"));
        
        
        table.getColumns().addAll(name,playedGames,winRatio);
        try {
            ObservableList<StatisticsBuilder> data = FXCollections.observableArrayList();
            
            statDao.findAll().stream().forEach(s-> {
                data.add(s);
            });
            
            table.setItems(data);
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }

        view.getChildren().addAll(header, table);
        dialog.getDialogPane().setContent(view);

        ButtonType cancelButton = new ButtonType("Takaisin", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(cancelButton);

        dialog.showAndWait();
    }

}
