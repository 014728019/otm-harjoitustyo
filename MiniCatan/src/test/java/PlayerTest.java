
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
        
        this.player1.giveResources(Resource.Corp, 1);
        this.player1.giveResources(Resource.Clay, 1);
        this.player1.giveResources(Resource.Stone, 1);
        this.player1.giveResources(Resource.Sheep, 1);
        this.player1.giveResources(Resource.Wood, 1);
    }
    
    @Test
    public void testSetUpIsRight() {
        assertEquals(Color.BLUE, this.player1.getColor());
        int clay = this.player1.getResources().get(Resource.Clay);
        int sheep = this.player1.getResources().get(Resource.Sheep);
        int wood = this.player1.getResources().get(Resource.Wood);
        int stone = this.player1.getResources().get(Resource.Stone);
        int corp = this.player1.getResources().get(Resource.Corp);
        assertEquals(1, clay);
        assertEquals(1, corp);
        assertEquals(1, wood);
        assertEquals(1, sheep);
        assertEquals(1, stone);
    }
    
    @Test
    public void testMakeRoad() {
        assertTrue(this.player1.makeRoad());
        int clay = this.player1.getResources().get(Resource.Clay);
        int sheep = this.player1.getResources().get(Resource.Sheep);
        int wood = this.player1.getResources().get(Resource.Wood);
        int stone = this.player1.getResources().get(Resource.Stone);
        int corp = this.player1.getResources().get(Resource.Corp);
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
        int clay = this.player1.getResources().get(Resource.Clay);
        int sheep = this.player1.getResources().get(Resource.Sheep);
        int wood = this.player1.getResources().get(Resource.Wood);
        int stone = this.player1.getResources().get(Resource.Stone);
        int corp = this.player1.getResources().get(Resource.Corp);
        assertEquals(0, clay);
        assertEquals(0, corp);
        assertEquals(0, wood);
        assertEquals(0, sheep);
        assertEquals(1, stone);
    }
    
    @Test
    public void testUpgradeBuildingAndGiveResources() {
        assertFalse(this.player1.upgradeBuilding());
        this.player1.giveResources(Resource.Corp, 1);
        this.player1.giveResources(Resource.Stone, 2);
        assertTrue(this.player1.upgradeBuilding());
        int clay = this.player1.getResources().get(Resource.Clay);
        int sheep = this.player1.getResources().get(Resource.Sheep);
        int wood = this.player1.getResources().get(Resource.Wood);
        int stone = this.player1.getResources().get(Resource.Stone);
        int corp = this.player1.getResources().get(Resource.Corp);
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
    public void testSetAndGetWinPoints() {
        assertEquals(0, this.player1.getWinPoints());
        this.player1.setWinPoints(2);
        assertEquals(2, this.player1.getWinPoints());
    }
    
    @Test
    public void testComparable() {
        Player player2 = new Player("TestPlayer2",Color.BLUE);
        assertEquals(0, player2.compareTo(this.player1));
        
        player2.setWinPoints(1);
        assertEquals(-1, player2.compareTo(this.player1));
        
        this.player1.setWinPoints(2);
        assertEquals(1, player2.compareTo(this.player1));
    }
}
