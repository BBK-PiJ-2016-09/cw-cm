import java.util.Calendar;
import java.util.Set;
import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class ContactManagerImplTests {

    @Test
    public void addFutureMeetingTest() {
        Calendar myDate = Calendar.getInstance();
        Contact testContact = new ContactImpl("contactName");
        Set<Contact> testContactSet = new HashSet<>();
        ContactManagerImpl testContactManager = new ContactManagerImpl();

        assertEquals(testContactManager.addFutureMeeting(myDate, testContactSet), 1);
    }
    
}