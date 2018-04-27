
import com.mycompany.domain.Building;
import com.mycompany.domain.Player;
import com.mycompany.domain.Resource;
import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {
    private Player player1;
    
    @Before
    public void setUp() {
        this.player1 = new Player("TestPlayer",Color.BLUE);
        
        this.player1.giveResources(Resource.Vilja, 1);
        this.player1.giveResources(Resource.Savi, 1);
        this.player1.giveResources(Resource.Kivi, 1);
        this.player1.giveResources(Resource.Lammas, 1);
        this.player1.giveResources(Resource.Puu, 1);
    }
    
    @Test
    public void testSetUpIsRight() {
        assertEquals(Color.BLUE, this.player1.getColor());
        int clay = this.player1.getResources().get(Resource.Savi);
        int sheep = this.player1.getResources().get(Resource.Lammas);
        int wood = this.player1.getResources().get(Resource.Puu);
        int stone = this.player1.getResources().get(Resource.Kivi);
        int corp = this.player1.getResources().get(Resource.Vilja);
        assertEquals(1, clay);
        assertEquals(1, corp);
        assertEquals(1, wood);
        assertEquals(1, sheep);
        assertEquals(1, stone);
    }
    
    @Test
    public void testMakeRoad() {
        assertTrue(this.player1.makeRoad());
        int clay = this.player1.getResources().get(Resource.Savi);
        int sheep = this.player1.getResources().get(Resource.Lammas);
        int wood = this.player1.getResources().get(Resource.Puu);
        int stone = this.player1.getResources().get(Resource.Kivi);
        int corp = this.player1.getResources().get(Resource.Vilja);
        assertEquals(0, clay);
        assertEquals(1, corp);
        assertEquals(0, wood);
        assertEquals(1, sheep);
        assertEquals(1, stone);
        assertFalse(this.player1.makeRoad());
    }
    
    @Test
    public void testMakeBuilding() {
        assertTrue(this.player1.makeBuilding());
        assertFalse(this.player1.makeBuilding());
        int clay = this.player1.getResources().get(Resource.Savi);
        int sheep = this.player1.getResources().get(Resource.Lammas);
        int wood = this.player1.getResources().get(Resource.Puu);
        int stone = this.player1.getResources().get(Resource.Kivi);
        int corp = this.player1.getResources().get(Resource.Vilja);
        assertEquals(0, clay);
        assertEquals(0, corp);
        assertEquals(0, wood);
        assertEquals(0, sheep);
        assertEquals(1, stone);
    }
    
    @Test
    public void testUpgradeBuildingAndGiveResources() {
        assertFalse(this.player1.upgradeBuilding());
        this.player1.giveResources(Resource.Vilja, 1);
        this.player1.giveResources(Resource.Kivi, 2);
        assertTrue(this.player1.upgradeBuilding());
        int clay = this.player1.getResources().get(Resource.Savi);
        int sheep = this.player1.getResources().get(Resource.Lammas);
        int wood = this.player1.getResources().get(Resource.Puu);
        int stone = this.player1.getResources().get(Resource.Kivi);
        int corp = this.player1.getResources().get(Resource.Vilja);
        assertEquals(1, clay);
        assertEquals(0, corp);
        assertEquals(1, wood);
        assertEquals(1, sheep);
        assertEquals(0, stone);
    }
    
    @Test
    public void testCorrectName() {
        assertEquals("TestPlayer", this.player1.getName());
    }
    
    @Test
    public void testPlayerEquals() {
        assertFalse(this.player1.equals(null));
        assertFalse(this.player1.equals(3));
        assertTrue(this.player1.equals(new Player("TestPlayer", null)));
        assertFalse(this.player1.equals(new Player("TestPlayer2", null)));
    }
    
    @Test
    public void testPlayersStatus() {
        String status = "TestPlayer"+
                "\n\tSavi: " +1+
                "\n\tPuu: " +1+
                "\n\tLammas: " +1+
                "\n\tVilja: " +1+
                "\n\tKivi: " +1;
        
        assertEquals(status, this.player1.getStatus());
    }
    
    @Test
    public void testGetWinPoints() {
        assertEquals(0, this.player1.getWinPoints());
        this.player1.getBuildings().add(new Building(this.player1));
        assertEquals(1, this.player1.getWinPoints());
    }
    
    @Test
    public void testComparable() {
        Player player2 = new Player("TestPlayer2",Color.BLUE);
        assertEquals(0, player2.compareTo(this.player1));
        
        player2.getBuildings().add(new Building(player2));
        assertEquals(-1, player2.compareTo(this.player1));

    }
}
