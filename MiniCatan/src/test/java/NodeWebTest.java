
import com.mycompany.domain.Node;
import com.mycompany.domain.NodeWeb;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class NodeWebTest {

    private NodeWeb nodeWeb;


    @Before
    public void setUp() {
        this.nodeWeb = new NodeWeb();
    }
    
    @Test
    public void testListSize() {
        assertTrue(this.nodeWeb.getNodes().values().size() == 54);
    }
    
    @Test
    public void testNeighbours() {
        HashMap<String, ArrayList<String>> isAnNeighbourTo = new HashMap<>();
        
        for (Node n : this.nodeWeb.getNodes().values()) {
            for (String n1 : n.getNeighbours()) {
                isAnNeighbourTo.putIfAbsent(n1, new ArrayList<String>());
                isAnNeighbourTo.get(n1).add(n.getId());
            }
        }
        
        this.nodeWeb.getNodes().values().stream().forEach(n-> {
            assertTrue(n.getNeighbours().size() == isAnNeighbourTo.get(n.getId()).size());
            n.getNeighbours().stream().forEach(n1-> {
                assertTrue(isAnNeighbourTo.get(n.getId()).contains(n1));
            });
        });
    }

}
