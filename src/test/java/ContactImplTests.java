import org.junit.*;
import static org.junit.Assert.*;

public class ContactImplTests {

    Contact testContact = new ContactImpl("contactName");


    @Test
    public void getNameTest() {
        assertEquals(testContact.getName(), "contactName");
    }
    @Test
    public void getIdTest() {
        assertEquals(testContact.getId(), 3);

    }
    @Test
    public void addAndGetNotesTest() {
        testContact.addNotes("Some notes on this contact");
        assertEquals(testContact.getNotes(), "Some notes on this contact");

    }
}
