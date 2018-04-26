package com.mycompany.gui;

import com.mycompany.domain.Player;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class InfoView implements View{
    
    @Override
    public void show(Stage stage) {
        Dialog<List<Player>> dialog = new Dialog<>();
        VBox view = new VBox();
        dialog.getDialogPane().setContent(view);

        Label head = new Label("Peli info:");
        head.setFont(Font.font(18));

        TextArea text = new TextArea();
        text.setPrefSize(500, 400);
        text.setEditable(false);

        try {
//            Files.lines(Paths.get("GameInfo.txt"), StandardCharsets.UTF_8).forEach(l -> {
//                text.setText(text.getText() + l + "\n");
//            });

            InputStream in = getClass().getResourceAsStream("/GameInfo.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            
            reader.lines().forEach(l-> {
                text.setText(text.getText() + l + "\n");
            });
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }

        view.getChildren().addAll(head, text);
        dialog.getDialogPane().setContent(view);

        ButtonType cancelButton = new ButtonType("Takaisin", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(cancelButton);

        dialog.showAndWait();
    }

}
