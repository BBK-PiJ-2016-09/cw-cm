import org.junit.*;
import static org.junit.Assert.*;

public class ContactImplTests {


    @Test
    public void getNameTest() {
        Contact testContact = new ContactImpl();
        assertEquals(testContact.getName(), "contactName");

    }
}
