
import com.mycompany.domain.Building;
import com.mycompany.domain.Location;
import com.mycompany.domain.Node;
import com.mycompany.domain.Player;
import com.mycompany.domain.Resource;
import com.mycompany.domain.Road;
import com.mycompany.logics.Game;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {
    private Game game;
    
    @Before
    public void setUp() {
        ArrayList<Player> players = new ArrayList(Arrays.asList(new Player("Pelaaja1",null), new Player("Pelaaja2",null)));
        this.game = new Game(players, 5);
    }
    
    @Test
    public void testTurn() {
        assertFalse(this.game.isAfterInitRounds());
    }
    
    @Test
    public void testDicesAndTurn() {
        assertTrue(this.game.getDices().getThrowed() == 0);
        this.game.throwDice();
        assertTrue(this.game.getDices().getThrowed() != 0);
        assertTrue(this.game.getPlayerOnTurn().getName().equals("Pelaaja1"));
        this.game.nextTurn();
        assertTrue(this.game.getPlayerOnTurn().getName().equals("Pelaaja2"));
    }
    
    @Test
    public void testPlayer() {
        assertTrue(this.game.getPlayers().contains(new Player("Pelaaja1", null)));
        assertFalse(this.game.getPlayers().contains(new Player("Pelaaja11", null)));
    }
    
    @Test
    public void testTestEnd() {
        assertFalse(this.game.testEnd());
        this.game.getPlayerOnTurn().getBuildings().add(new Building(this.game.getPlayerOnTurn()));
        this.game.getPlayerOnTurn().getBuildings().add(new Building(this.game.getPlayerOnTurn()));
        this.game.getPlayerOnTurn().getBuildings().add(new Building(this.game.getPlayerOnTurn()));
        this.game.getPlayerOnTurn().getBuildings().add(new Building(this.game.getPlayerOnTurn()));
        this.game.getPlayerOnTurn().getBuildings().add(new Building(this.game.getPlayerOnTurn()));
        assertTrue(this.game.testEnd());
    }
    
    @Test
    public void testLittleGameScenario() {
        Node n1 = this.game.getNodeWeb().getNode("N7");
        Node n2 = this.game.getNodeWeb().getNode("N8");
        Node n3 = this.game.getNodeWeb().getNode("N13");
        Node n4 = this.game.getNodeWeb().getNode("N3");
        
        assertTrue(this.game.getPlayerOnTurn().getName().equals("Pelaaja1"));
        assertTrue(this.game.getPlayerOnTurn().getBuildings().size() == 0);
        this.game.clickNode(n1);
        
        assertTrue(this.game.getPlayerOnTurn().getName().equals("Pelaaja2"));
        assertTrue(this.game.getPlayerOnTurn().getBuildings().size() == 0);
        this.game.clickNode(this.game.getNodeWeb().getNode("N21"));
        
        assertTrue(this.game.getPlayerOnTurn().getName().equals("Pelaaja2"));
        assertTrue(this.game.getPlayerOnTurn().getBuildings().size() == 1);
        this.game.clickNode(this.game.getNodeWeb().getNode("N31"));
        
        assertTrue(this.game.getPlayerOnTurn().getName().equals("Pelaaja1"));
        assertTrue(this.game.getPlayerOnTurn().getBuildings().size() == 1);
        this.game.clickNode(this.game.getNodeWeb().getNode("N40"));
        assertTrue(this.game.getPlayerOnTurn().getBuildings().size() == 2);
        
        for (int i = 0; i < 10; i++) {
            this.game.throwDice();
            this.game.nextTurn();
        }
        
        assertTrue(this.game.getPlayerOnTurn().getName().equals("Pelaaja1"));
        
        Road r = new Road(new Player("test", null), n1, n3);
        this.game.clickRoad(r);
        assertTrue(this.game.getPlayerOnTurn().getRoads().size() == 0);
        
        Road r1 = new Road(null, n1, n2);
        this.game.getPlayerOnTurn().giveResources(Resource.Savi, 3);
        this.game.getPlayerOnTurn().giveResources(Resource.Puu, 3);
        this.game.clickRoad(r1);
        assertTrue(this.game.getPlayerOnTurn().getRoads().size() == 1);
        
        assertTrue(this.game.isAfterInitRounds());
        
        this.game.getPlayerOnTurn().giveResources(Resource.Puu, 3);
        assertTrue(this.game.changeResources(Resource.Puu, Resource.Lammas));
        
        this.game.getPlayerOnTurn().giveResources(Resource.Lammas, 1);
        this.game.getPlayerOnTurn().giveResources(Resource.Vilja, 3);
        this.game.clickNode(n2);
        assertTrue(this.game.getPlayerOnTurn().getBuildings().size() == 2);
        
        Road r2 = new Road(null, n2, n4);
        this.game.clickRoad(r2);
        assertTrue(this.game.getPlayerOnTurn().getRoads().size() == 2);
        
        this.game.clickNode(n4);
        assertTrue(this.game.getPlayerOnTurn().getBuildings().size() == 3);
    }

}
