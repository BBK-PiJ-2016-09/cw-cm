import org.junit.*;
import static org.junit.Assert.*;

public class ContactImplTests.java {
    @Test
    public void getIdTest() {
        Contact testContact = new Contact("contactName");
        assertTrue(testContact.getName() == "contactName");
    }
}
