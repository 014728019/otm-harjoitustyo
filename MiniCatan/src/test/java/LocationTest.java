
import com.mycompany.domain.Location;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LocationTest {
    private Location loca1;
    
    @Before
    public void setUp() {
        this.loca1 = new Location(1,1);
    }
    
    @Test
    public void locationEqualsAnother() {
        assertTrue(loca1.equals(new Location(1,1)));
        assertFalse(loca1.equals(new Location(1,2)));
        assertFalse(loca1.equals(new Location(2,1)));
        assertFalse(loca1.equals(null));
    }
    
    @Test
    public void getRightCoordinates() {
        assertEquals(loca1.getX(),1);
        assertEquals(loca1.getY(),1);
    }
    
}
