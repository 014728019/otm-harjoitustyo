
import com.mycompany.domain.Field;
import com.mycompany.domain.Location;
import com.mycompany.domain.Player;
import com.mycompany.domain.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FieldTest {
    private Field field;
    
    @Before
    public void setUp() {
        this.field = new Field(Resource.Vilja, 7, null, null);
    }
    
    @Test
    public void testResource() {
        assertFalse(Resource.Savi.equals(this.field.getResource()));
        assertTrue(Resource.Vilja.equals(this.field.getResource()));
    }
    
    @Test
    public void testValue() {
        assertFalse(this.field.getValue() == 99);
        assertTrue(this.field.getValue() == 7);
    }
    
    @Test
    public void testSetLocationAndNode() {
        ArrayList<String> list = new ArrayList<>();
        list.add("N1");
        list.add("N2");
        this.field.setLocationAndNode(new Location(1,1), list);
        assertTrue(this.field.getLocation().equals(new Location(1,1)));
        assertTrue(this.field.getNodes().contains("N1"));
    }
    
}
