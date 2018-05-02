
import com.mycompany.domain.StatisticsBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsBuilderTest {
    private StatisticsBuilder sb;

    @Before
    public void setUp() {
        this.sb = new StatisticsBuilder("Pelaaja1");
    }
    
    @Test
    public void testAdd() {
        assertEquals(0, this.sb.getGamesCount());
        this.sb.add(2, 2, 2, 2);
        assertEquals(1, this.sb.getGamesCount());
        assertTrue(0.0 == this.sb.getWinRatio());
        this.sb.add(2, 1, 1, 1);
        assertTrue(0.5 == this.sb.getWinRatio());
        assertTrue(1.0 == this.sb.getAverageWinPointsFromLimit());
    }
    
    @Test
    public void testGetPlayer() {
        assertTrue("Pelaaja1".equals(sb.getName()));
    }

}
