import org.junit.*;
import static org.junit.Assert.*;

public class ContactImplTests {
    private Contact testContact;
    @Before
    public void multipleInit(){
        testContact = new ContactImpl(1, "contactName");
    }

    @Test
    public void getNameTest() {

        assertEquals(testContact.getName(), "contactName");

    }
}
