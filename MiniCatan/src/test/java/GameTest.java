
import com.mycompany.domain.Player;
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
    }

}
