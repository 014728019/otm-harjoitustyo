
package domain;

public class Building {
    private Player player;
    private int value;
    
    public Building(Player player) {
        this.player = player;
        this.value = 1;
    }
    
    public void upgrade() {
        this.value++;
        System.out.println("Pelaajan: "+this.player.getName()+" rakennus päivitetty tasolle: "+this.value);
    }
    
    public void produce(Resource resource) {
        this.player.giveResources(resource, this.value);
    }

    public int getValue() {
        return value;
    }

    public Player getPlayer() {
        return player;
    }
    
}
