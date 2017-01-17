import org.junit.*;
import static org.junit.Assert.*;

public class ContactImplTests {


    @Test
    public void getNameTest() {
        Contact testContact = new ContactImpl("contactName");
        assertEquals(testContact.getName(), "contactName");

    }
    @Test
    public void getIdTest() {
        Contact testContact = new ContactImpl("contactName");
        assertEquals(testContact.getId(), 2);

    }
}
