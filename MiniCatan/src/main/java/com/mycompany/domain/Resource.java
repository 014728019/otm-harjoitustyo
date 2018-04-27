
package com.mycompany.domain;

import javafx.scene.paint.Color;

public enum Resource {
    Vilja(Color.GOLDENROD), 
    Savi(Color.FIREBRICK), 
    Kivi(Color.GREY), 
    Puu(Color.GREEN), 
    Lammas(Color.YELLOWGREEN);
    
    private Color color;

    private Resource(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }
}
