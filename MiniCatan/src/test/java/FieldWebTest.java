
import com.mycompany.domain.FieldWeb;
import com.mycompany.domain.Node;
import com.mycompany.domain.NodeWeb;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FieldWebTest {
    private FieldWeb fw;
    
    @Before
    public void setUp() {
        this.fw = new FieldWeb();
    }
    
    @Test
    public void testFields() {
        NodeWeb nw = new NodeWeb();
        this.fw.getFields().stream().forEach(f-> {
            assertTrue(f.getNodes().size() == 6);
            
            f.getNodes().stream().forEach(n-> {
                int count = 0;
                for (String n1 : nw.getNode(n).getNeighbours()) {
                    if (f.getNodes().contains(n1)) {
                        count++;
                    }
                }
                assertTrue(count == 2);
            });
        });
        
        
    }
}
