
import com.mycompany.domain.Building;
import com.mycompany.domain.Player;
import com.mycompany.domain.Resource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BuildingTest {
    private Player player;
    private Building building;
    
    @Before
    public void setUp() {
        this.player = new Player("Player", null);
        this.building = new Building(this.player);
    }
    
    @Test
    public void setUpIsRight() {
        assertEquals(new Player("Player", null), this.building.getPlayer());
        assertEquals(1, this.building.getValue());
    }
    
    @Test
    public void testUpgrade() {
        assertEquals(1, this.building.getValue());
        this.building.upgrade();
        assertEquals(2, this.building.getValue());
    }
    
    @Test
    public void testProduce() {
        int clay = this.player.getResources().get(Resource.Savi);
        int sheep = this.player.getResources().get(Resource.Lammas);
        int wood = this.player.getResources().get(Resource.Puu);
        int stone = this.player.getResources().get(Resource.Kivi);
        int corp = this.player.getResources().get(Resource.Vilja);
        assertEquals(0, clay);
        assertEquals(0, corp);
        assertEquals(0, wood);
        assertEquals(0, sheep);
        assertEquals(0, stone);
        this.building.produce(Resource.Vilja);
        this.building.produce(Resource.Savi);
        this.building.produce(Resource.Lammas);
        this.building.produce(Resource.Kivi);
        this.building.produce(Resource.Puu);
        clay = this.player.getResources().get(Resource.Savi);
        sheep = this.player.getResources().get(Resource.Lammas);
        wood = this.player.getResources().get(Resource.Puu);
        stone = this.player.getResources().get(Resource.Kivi);
        corp = this.player.getResources().get(Resource.Vilja);
        assertEquals(1, clay);
        assertEquals(1, corp);
        assertEquals(1, wood);
        assertEquals(1, sheep);
        assertEquals(1, stone);
    }
    
}
