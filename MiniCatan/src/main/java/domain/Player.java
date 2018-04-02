
package domain;

import java.util.HashMap;
import java.util.Objects;
import javafx.scene.paint.Color;

public class Player {
    private String name;
    private HashMap<Resource, Integer> resources;
    private Color color;
    
    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.resources = new HashMap<>();
        
        this.resources.put(Resource.Clay, 0);
        this.resources.put(Resource.Corp, 0);
        this.resources.put(Resource.Sheep, 0);
        this.resources.put(Resource.Stone, 0);
        this.resources.put(Resource.Wood, 0);
    }

    public HashMap<Resource, Integer> getResources() {
        return resources;
    }
    
    public boolean makeRoad() {
        if (this.resources.get(Resource.Clay)>=1 && this.resources.get(Resource.Wood)>=1) {
            int clays = this.resources.get(Resource.Clay);
            int wood = this.resources.get(Resource.Wood);
            clays--;
            wood--;
            this.resources.put(Resource.Clay, clays);
            this.resources.put(Resource.Wood, wood);
            return true;
        }
        return false;
    }
    
    public boolean makeBuilding() {
        if (this.resources.get(Resource.Clay)>=1 && this.resources.get(Resource.Wood)>=1
                && this.resources.get(Resource.Sheep)>=1 && this.resources.get(Resource.Corp)>=1) {
            int clays = this.resources.get(Resource.Clay);
            int wood = this.resources.get(Resource.Wood);
            int corp = this.resources.get(Resource.Corp);
            int sheep = this.resources.get(Resource.Sheep);
            clays--;
            wood--;
            corp--;
            sheep--;
            this.resources.put(Resource.Clay, clays);
            this.resources.put(Resource.Wood, wood);
            this.resources.put(Resource.Corp, corp);
            this.resources.put(Resource.Sheep, sheep);
            return true;
        }
        return false;
    }
    
    public boolean upgradeBuilding() {
        if (this.resources.get(Resource.Stone)>=3 && this.resources.get(Resource.Corp)>=2) {
            int stone = this.resources.get(Resource.Stone);
            int corp = this.resources.get(Resource.Corp);
            stone = stone -3;
            corp = corp -2;
            this.resources.put(Resource.Corp, corp);
            this.resources.put(Resource.Stone, stone);
            return true;
        }
        return false;
    }
    
    public void giveResources(Resource resource, int number) {
        this.resources.replace(resource, this.resources.get(resource)+number);
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
    
    public String getStatus() {
        return this.name+
                "\n\tResurssit:"+
                "\n\t\tSavi: " +this.resources.get(Resource.Clay)+
                "\n\t\tPuu: " +this.resources.get(Resource.Wood)+
                "\n\t\tLammas: " +this.resources.get(Resource.Sheep)+
                "\n\t\tVilja: " +this.resources.get(Resource.Corp)+
                "\n\t\tKivi: " +this.resources.get(Resource.Stone);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
}
