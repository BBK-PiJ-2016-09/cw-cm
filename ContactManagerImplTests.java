import org.junit.*;
import static org.junit.Assert.*;

public class ContactManagerImplTests {

    @Test
    public void getNameTest() {
        assertEquals(testContact.getName(), "contactName");
    }
}