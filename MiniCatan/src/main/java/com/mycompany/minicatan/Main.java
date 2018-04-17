
package com.mycompany.minicatan;

import com.mycompany.gui.MenuView;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws ClassNotFoundException {
        stage.setTitle("MiniCatan");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
        
        MenuView menu = new MenuView();
        menu.show(stage);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
