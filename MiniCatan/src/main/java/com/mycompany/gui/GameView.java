package com.mycompany.gui;

import com.mycompany.database.Database;
import com.mycompany.database.PlayerDao;
import com.mycompany.domain.Building;
import com.mycompany.domain.Player;
import com.mycompany.domain.Road;
import com.mycompany.logics.Game;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameView implements View {

    private Canvas underlay;
    private Game game;
    private Stage stage;
    private final int scalerY = 60;
    private final int scalerX = 54;
    private StackPane map = new StackPane();
    private Pane pane = new Pane();
    private GraphicsContext plotter;
    private VBox players = new VBox();
    private Label statusPlayers = new Label();
    private Label statusPoints = new Label();
    private Label statusTurn = new Label();
    private BorderPane root = new BorderPane();
    private Scene menuScene;

    public GameView() throws Exception {
        PlayerDao playerDao = null;
        List<Player> choices = null;
        try {
            playerDao = new PlayerDao(new Database("jdbc:sqlite:MiniCatanDatabase.db"));
            choices = playerDao.findAll();
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }

        Dialog<List<Player>> dialog = new Dialog<>();
        BorderPane root = new BorderPane();
        VBox view = new VBox();
        view.setPrefHeight(220);
        ChoiceBox choice = new ChoiceBox();
        ChoiceBox winpointChoice = new ChoiceBox(FXCollections.observableArrayList(8, 10, 12));
        winpointChoice.getSelectionModel().select(1);
        choice.setPrefWidth(200);
        Button newGame = new Button("Aloita uusi peli!");

        ArrayList<Color> colors = new ArrayList(Arrays.asList(Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW));

        choices.stream().map(m -> m.getName()).forEach(p -> {
            choice.getItems().add(p);
        });

        view.getChildren().addAll(new Label("Valitse vaadittu pistemäärä voittoon: "), winpointChoice, new Label("\nLisää pelaaja: "), choice, new Label("\nLisätyt pelaajat(vaaditaan 2-4):"));

        ArrayList<Player> players = new ArrayList<>();

        choice.setOnAction((event) -> {
            if (players.size() < 4) {
                players.add(new Player(choice.getValue().toString(), colors.get(0)));
                colors.remove(0);
                view.getChildren().add(new Label(choice.getValue().toString()));
                choice.getItems().remove(choice.getValue());
            }
        });

        newGame.setOnAction((event) -> {

            if (players.size() >= 2 && winpointChoice.getValue() != null) {
                this.game = new Game(players, (int) winpointChoice.getValue());
                this.initView();
                dialog.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Pelaajia oltava 2-4!");
                alert.showAndWait();
            }

        });

        ButtonType cancelButton = new ButtonType("Takaisin", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(cancelButton);

        root.setCenter(view);
        root.setBottom(newGame);

        dialog.getDialogPane().setContent(root);
        dialog.showAndWait();
    }

    @Override
    public void show(Stage stage) {
        this.stage = stage;
        this.menuScene = stage.getScene();
        this.stage.setScene(new Scene(this.root));
        this.stage.centerOnScreen();
    }

    public void initView() {
        this.underlay = new Canvas(13 * scalerX, 12 * scalerY);
        this.map.getChildren().addAll(underlay, pane);
        this.plotter = underlay.getGraphicsContext2D();
        ArrayList<Road> roads = new ArrayList<>();

        this.game.getNodeWeb().getNodes().values().stream().forEach(n -> {
            n.getNeighbours().stream().forEach(k -> {
                Road r = new Road(null, this.game.getNodeWeb().getNode(k), n);
                if (!roads.contains(n)) {
                    roads.add(r);
                }
            });
        });

        roads.stream().forEach(r -> {
            plotter.strokeLine(r.getLocation1().getX() * scalerX, r.getLocation1().getY() * scalerY,
                    r.getLocation2().getX() * scalerX, r.getLocation2().getY() * scalerY);
            int x = (r.getLocation1().getX() + r.getLocation2().getX()) * scalerX / 2 - 5;
            int y = (r.getLocation1().getY() + r.getLocation2().getY()) * scalerY / 2 - 5;
            plotter.fillOval(x, y, 10, 10);

            if (!this.game.getRoads().contains(r)) {
                Button b1 = new Button("");
                b1.setPrefSize(24, 24);
                b1.setBackground(Background.EMPTY);

                b1.setOnAction((event) -> {
                    Road newRoad = this.game.clickRoad(r);
                    if (newRoad != null) {
                        plotter.setStroke(newRoad.getPlayer().getColor());
                        plotter.setLineWidth(6);
                        plotter.strokeLine(newRoad.getLocation1().getX() * scalerX, newRoad.getLocation1().getY() * scalerY,
                                newRoad.getLocation2().getX() * scalerX, newRoad.getLocation2().getY() * scalerY);
                    }
                    System.out.println("Road clicked.");
                    this.refreshStatus();
                });

                b1.setLayoutX((((double) r.getLocation1().getX() + r.getLocation2().getX()) / 2) * scalerX - 12);
                b1.setLayoutY((((double) r.getLocation1().getY() + r.getLocation2().getY()) / 2) * scalerY - 12);

                pane.getChildren().add(b1);
            }

        });

        game.getNodeWeb().getNodes().values().forEach(k -> {
            plotter.fillOval(k.getLocation().getX() * scalerX - 10, k.getLocation().getY() * scalerY - 10, 20, 20);

            Button b1 = new Button("");
            b1.setOnAction((event) -> {
                System.out.println(k.getId() + " clicked");
                Building newbuilding = this.game.clickNode(k);
                if (newbuilding != null) {
                    plotter.setFill(newbuilding.getPlayer().getColor());
                    plotter.setStroke(newbuilding.getPlayer().getColor());
                    plotter.setLineWidth(1);
                    plotter.fillOval(k.getLocation().getX() * scalerX - 10, k.getLocation().getY() * scalerY - 10, 20, 20);
                    if (k.getBuilding().getValue() == 2) {
                        plotter.strokeOval(k.getLocation().getX() * scalerX - 12, k.getLocation().getY() * scalerY - 12, 24, 24);
                    }
                    if (k.getBuilding().getValue() == 3) {
                        plotter.strokeOval(k.getLocation().getX() * scalerX - 14, k.getLocation().getY() * scalerY - 14, 28, 28);
                    }
                }
                this.refreshStatus();
            });
            b1.setPrefSize(30, 30);
            b1.setBackground(Background.EMPTY);
            b1.setLayoutX((k.getLocation().getX() * scalerX) - 15);
            b1.setLayoutY((k.getLocation().getY() * scalerY) - 15);

            pane.getChildren().add(b1);

        });

        game.getFieldWeb().getFields().stream().forEach(f -> {
            plotter.setFont(Font.font(14));
            plotter.fillText(f.getValue() + ": " + f.getResource(), f.getLocation().getX() * scalerX, f.getLocation().getY() * scalerY);
        });

        Label playersAndResources = new Label("Pelaajat ja resurssit:");
        playersAndResources.setFont(Font.font(16));
        Label victoryPoints = new Label("\nPelaajien voittopisteet:");
        victoryPoints.setFont(Font.font(16));
        Button throwTurn = new Button("Seuraava vuoro");

        throwTurn.setOnAction((event) -> {
            if (game.getTurn().realTurn()) {
                this.game.throwDice();
                this.game.nextTurn();
                this.refreshStatus();
            }
        });

        players.getChildren().addAll(playersAndResources, statusPlayers, victoryPoints, statusPoints, statusTurn, throwTurn);

        this.root.setLeft(this.players);
        this.root.setCenter(this.map);
        this.refreshStatus();
    }

    private void refreshStatus() {
        long a = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        Runtime.getRuntime().gc();
        long b = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("FreedMemory(%): " + (double) (a - b) / Runtime.getRuntime().totalMemory() * 100);

        this.statusPlayers.setText("");
        for (Player p : game.getPlayers()) {
            this.statusPlayers.setText(this.statusPlayers.getText() + p.getStatus() + "\n");
        }

        this.statusPoints.setText("");
        for (Player p : game.getPlayers()) {
            this.statusPoints.setText(this.statusPoints.getText() + p.getName() + ": " + p.getWinPoints() + "\n");
        }

        this.statusTurn.setText("");
        this.statusTurn.setText("\nVuorossa: " + game.getPlayerOnTurn().getName());

        if (this.game.isEnded()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText("Peli päättyi!");
            String text = "Pelaajat ja pisteet:\n";
            for (Player p : this.game.getPlayers()) {
                text = text.concat(p.getName()+": "+p.getWinPoints()+"\n");
            }
            alert.setContentText(text);
            alert.showAndWait();
            this.stage.setScene(this.menuScene);
            this.stage.centerOnScreen();
        }
    }

}
