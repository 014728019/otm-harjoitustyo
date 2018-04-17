
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
        int clay = this.player.getResources().get(Resource.Clay);
        int sheep = this.player.getResources().get(Resource.Sheep);
        int wood = this.player.getResources().get(Resource.Wood);
        int stone = this.player.getResources().get(Resource.Stone);
        int corp = this.player.getResources().get(Resource.Corp);
        assertEquals(0, clay);
        assertEquals(0, corp);
        assertEquals(0, wood);
        assertEquals(0, sheep);
        assertEquals(0, stone);
        this.building.produce(Resource.Corp);
        this.building.produce(Resource.Clay);
        this.building.produce(Resource.Sheep);
        this.building.produce(Resource.Stone);
        this.building.produce(Resource.Wood);
        clay = this.player.getResources().get(Resource.Clay);
        sheep = this.player.getResources().get(Resource.Sheep);
        wood = this.player.getResources().get(Resource.Wood);
        stone = this.player.getResources().get(Resource.Stone);
        corp = this.player.getResources().get(Resource.Corp);
        assertEquals(1, clay);
        assertEquals(1, corp);
        assertEquals(1, wood);
        assertEquals(1, sheep);
        assertEquals(1, stone);
    }
    
}
