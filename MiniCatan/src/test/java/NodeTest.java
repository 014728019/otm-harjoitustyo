
import com.mycompany.domain.Building;
import com.mycompany.domain.Location;
import com.mycompany.domain.Node;
import com.mycompany.domain.Player;
import com.mycompany.domain.Resource;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NodeTest {
    private Node node;
    
    @Before
    public void setUp() {
        this.node = new Node("N1", new Location(1,1), Arrays.asList("N2", "N3", "N4"));
    }
    
    @Test
    public void testGetters() {
        assertEquals("N1",this.node.getId());
        assertEquals(null,this.node.getBuilding());
        assertEquals(new Location(1,1),this.node.getLocation());
        assertTrue(this.node.getNeighbours().contains("N3"));
    }
    
    @Test
    public void testBuilding() {
        assertEquals(null,this.node.getBuilding());
        this.node.makeBuilding(new Building(new Player("Timo",null)));
        assertEquals("Timo",this.node.getBuilding().getPlayer().getName());
        
        assertEquals(1,this.node.getBuilding().getValue());
        this.node.upgradeBuilding();
        assertEquals(2,this.node.getBuilding().getValue());
        
        int clays = this.node.getBuilding().getPlayer().getResources().get(Resource.Savi);
        assertEquals(0,clays);
        this.node.produce(Resource.Savi);
        clays = this.node.getBuilding().getPlayer().getResources().get(Resource.Savi);
        assertEquals(2,clays);
    }

}
