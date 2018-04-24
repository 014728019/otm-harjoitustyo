
import com.mycompany.domain.Building;
import com.mycompany.domain.Location;
import com.mycompany.domain.Node;
import com.mycompany.domain.Player;
import com.mycompany.domain.Road;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RoadTest {
    private Road road;
    private Road road1;
    
    @Before
    public void setUp() {
        Player p1 = new Player("Jester",null);
        Node n1 = new Node("N1",new Location(1,1),Arrays.asList("N2"));
        Node n2 = new Node("N2",new Location(2,2),Arrays.asList("N1","N3"));
        Node n3 = new Node("N3",new Location(3,3),Arrays.asList("N2"));
        this.road = new Road(p1, n1, n2);
        this.road1 = new Road(null,n2,n3);
    }
    
    @Test
    public void testNodes() {
        assertTrue(this.road.getNode1().getId().equals("N1"));
        assertTrue(this.road.getNode2().getId().equals("N2"));
    }
    
    @Test
    public void testLocations() {
        assertTrue(this.road.getLocation1().equals(new Location(1,1)));
        assertTrue(this.road.getLocation2().equals(new Location(2,2)));
    }
    
    @Test
    public void testPlayer() {
        assertTrue(this.road.getPlayer().getName().equals("Jester"));
    }
    
    @Test
    public void testInTouchRoad() {
        assertTrue(this.road.inTouch(road1));
        this.road.getNode2().makeBuilding(new Building(new Player("Wrong", null)));
        assertFalse(this.road.inTouch(road1));
    }
    
    @Test
    public void testInTouchNode() {
        assertTrue(this.road.inTouch(new Node("N1", null, Arrays.asList())));
        assertFalse(this.road.inTouch(new Node("N3", null, Arrays.asList())));
    }
    
    @Test
    public void testEquals() {
        assertTrue(this.road.equals(new Road(this.road.getPlayer(), this.road.getNode1(), this.road.getNode2())));
        assertTrue(this.road.equals(new Road(this.road.getPlayer(), this.road.getNode2(), this.road.getNode1())));
        assertFalse(this.road.equals(new Road(this.road.getPlayer(), this.road1.getNode2(), this.road.getNode1())));
        assertFalse(this.road.equals(null));
    }
}
