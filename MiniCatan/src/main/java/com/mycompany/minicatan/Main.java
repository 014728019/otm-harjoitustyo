
package com.mycompany.minicatan;

import graphic.GraphicLoader;
import graphic.InfoView;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws ClassNotFoundException {
        InfoView info = new InfoView();
        info.show();
        
        GraphicLoader gl = new GraphicLoader(stage);

        Scene scene = new Scene(gl.getMenuView());

        stage.setScene(scene);
        stage.setTitle("MiniCatan");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
