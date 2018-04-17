package domain;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;

public class NodeWeb {

    private HashMap<String, Node> nodes;

    public NodeWeb() {
        this.nodes = new HashMap<>();

        this.nodes.put("N1", new Node("N1", new Location(6, 1), Arrays.asList("N2", "N4")));
        this.nodes.put("N2", new Node("N2", new Location(7, 1), Arrays.asList("N1", "N5")));
        this.nodes.put("N3", new Node("N3", new Location(4, 2), Arrays.asList("N4", "N8")));
        this.nodes.put("N4", new Node("N4", new Location(5, 2), Arrays.asList("N3", "N1", "N9")));
        this.nodes.put("N5", new Node("N5", new Location(8, 2), Arrays.asList("N2", "N10", "N6")));
        this.nodes.put("N6", new Node("N6", new Location(9, 2), Arrays.asList("N5", "N11")));
        this.nodes.put("N7", new Node("N7", new Location(2, 3), Arrays.asList("N8", "N13")));
        this.nodes.put("N8", new Node("N8", new Location(3, 3), Arrays.asList("N7", "N3", "N14")));
        this.nodes.put("N9", new Node("N9", new Location(6, 3), Arrays.asList("N4", "N15", "N10")));
        this.nodes.put("N10", new Node("N10", new Location(7, 3), Arrays.asList("N9", "N5", "N16")));
        this.nodes.put("N11", new Node("N11", new Location(10, 3), Arrays.asList("N12", "N6", "N17")));
        this.nodes.put("N12", new Node("N12", new Location(11, 3), Arrays.asList("N11", "N18")));
        this.nodes.put("N13", new Node("N13", new Location(1, 4), Arrays.asList("N7", "N19")));
        this.nodes.put("N14", new Node("N14", new Location(4, 4), Arrays.asList("N8", "N20", "N15")));
        this.nodes.put("N15", new Node("N15", new Location(5, 4), Arrays.asList("N14", "N9", "N21")));
        this.nodes.put("N16", new Node("N16", new Location(8, 4), Arrays.asList("N10", "N17", "N22")));
        this.nodes.put("N17", new Node("N17", new Location(9, 4), Arrays.asList("N11", "N16", "N23")));
        this.nodes.put("N18", new Node("N18", new Location(12, 4), Arrays.asList("N12", "N24")));
        this.nodes.put("N19", new Node("N19", new Location(2, 5), Arrays.asList("N13", "N25", "N20")));
        this.nodes.put("N20", new Node("N20", new Location(3, 5), Arrays.asList("N19", "N14", "N26")));
        this.nodes.put("N21", new Node("N21", new Location(6, 5), Arrays.asList("N15", "N27", "N22")));
        this.nodes.put("N22", new Node("N22", new Location(7, 5), Arrays.asList("N21", "N16", "N28")));
        this.nodes.put("N23", new Node("N23", new Location(10, 5), Arrays.asList("N17", "N29", "N24")));
        this.nodes.put("N24", new Node("N24", new Location(11, 5), Arrays.asList("N18", "N23", "N30")));
        this.nodes.put("N25", new Node("N25", new Location(1, 6), Arrays.asList("N19", "N31")));
        this.nodes.put("N26", new Node("N26", new Location(4, 6), Arrays.asList("N20", "N27", "N32")));
        this.nodes.put("N27", new Node("N27", new Location(5, 6), Arrays.asList("N26", "N21", "N33")));
        this.nodes.put("N28", new Node("N28", new Location(8, 6), Arrays.asList("N22", "N29", "N34")));
        this.nodes.put("N29", new Node("N29", new Location(9, 6), Arrays.asList("N28", "N23", "N35")));
        this.nodes.put("N30", new Node("N30", new Location(12, 6), Arrays.asList("N24", "N36")));
        this.nodes.put("N31", new Node("N31", new Location(2, 7), Arrays.asList("N25", "N37", "N32")));
        this.nodes.put("N32", new Node("N32", new Location(3, 7), Arrays.asList("N31", "N26", "N38")));
        this.nodes.put("N33", new Node("N33", new Location(6, 7), Arrays.asList("N27", "N39", "N34")));
        this.nodes.put("N34", new Node("N34", new Location(7, 7), Arrays.asList("N28", "N33", "N40")));
        this.nodes.put("N35", new Node("N35", new Location(10, 7), Arrays.asList("N29", "N41", "N36")));
        this.nodes.put("N36", new Node("N36", new Location(11, 7), Arrays.asList("N35", "N30", "N42")));
        this.nodes.put("N37", new Node("N37", new Location(1, 8), Arrays.asList("N31", "N43")));
        this.nodes.put("N38", new Node("N38", new Location(4, 8), Arrays.asList("N32", "N39", "N44")));
        this.nodes.put("N39", new Node("N39", new Location(5, 8), Arrays.asList("N33", "N38", "N45")));
        this.nodes.put("N40", new Node("N40", new Location(8, 8), Arrays.asList("N34", "N46", "N41")));
        this.nodes.put("N41", new Node("N41", new Location(9, 8), Arrays.asList("N35", "N40", "N47")));
        this.nodes.put("N42", new Node("N42", new Location(12, 8), Arrays.asList("N36", "N48")));
        this.nodes.put("N43", new Node("N43", new Location(2, 9), Arrays.asList("N37", "N44")));
        this.nodes.put("N44", new Node("N44", new Location(3, 9), Arrays.asList("N38", "N43", "N49")));
        this.nodes.put("N45", new Node("N45", new Location(6, 9), Arrays.asList("N39", "N50", "N46")));
        this.nodes.put("N46", new Node("N46", new Location(7, 9), Arrays.asList("N40", "N45", "N51")));
        this.nodes.put("N47", new Node("N47", new Location(10, 9), Arrays.asList("N41", "N48", "N52")));
        this.nodes.put("N48", new Node("N48", new Location(11, 9), Arrays.asList("N42", "N47")));
        this.nodes.put("N49", new Node("N49", new Location(4, 10), Arrays.asList("N44", "N50")));
        this.nodes.put("N50", new Node("N50", new Location(5, 10), Arrays.asList("N49", "N45", "N53")));
        this.nodes.put("N51", new Node("N51", new Location(8, 10), Arrays.asList("N46", "N52", "N54")));
        this.nodes.put("N52", new Node("N52", new Location(9, 10), Arrays.asList("N51", "N47")));
        this.nodes.put("N53", new Node("N53", new Location(6, 11), Arrays.asList("N50", "N54")));
        this.nodes.put("N54", new Node("N54", new Location(7, 11), Arrays.asList("N53", "N51")));

        //this.referenceMap();
    }

    public HashMap<String, Node> getNodes() {
        return this.nodes;
    }

    public Node getNode(String id) {
        return this.nodes.get(id);
    }

    public void referenceMap() {
        try {
            NodeWeb nodeWeb = this;
            Canvas underlay = new Canvas(13 * 54, 12 * 60);
            GraphicsContext plotter = underlay.getGraphicsContext2D();
            plotter.setLineWidth(4);

            for (Node n : nodeWeb.getNodes().values()) {
                plotter.setStroke(Color.rgb(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
                n.getNeighbours().stream().map(m -> nodeWeb.getNode(m)).forEach(neigh -> {
                    plotter.strokeLine(
                            n.getLocation().getX() * 54,
                            n.getLocation().getY() * 60,
                            (((double) neigh.getLocation().getX() + n.getLocation().getX()) / 2) * 54,
                            (((double) neigh.getLocation().getY() + n.getLocation().getY()) / 2) * 60);
                });
            }

            WritableImage wi = new WritableImage((int) underlay.getWidth(), (int) underlay.getHeight());
            WritableImage snapshot = underlay.snapshot(new SnapshotParameters(), wi);
            File output = new File("ReferenceMap.png");
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", output);
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
    }
}
