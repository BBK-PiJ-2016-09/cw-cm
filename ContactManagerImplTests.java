import java.util.Calendar;
import java.util.Set;
import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class ContactManagerImplTests {
    Calendar myDate = Calendar.getInstance();
    Contact testContact = new ContactImpl("contactName");
    Set<Contact> testContactSet = new HashSet<>();
    ContactManagerImpl testContactManager = new ContactManagerImpl();
    FutureMeeting futureMeetingToBeAdded = new FutureMeetingImpl(myDate, testContactSet);

    @Test
    public void addFutureMeetingTest() {
        assertEquals(testContactManager.addFutureMeeting(myDate, testContactSet), futureMeetingToBeAdded.getId() + 1);
    }

    @Test
    public void getFutureMeetingTest() {
        Integer meetingId = testContactManager.addFutureMeeting(myDate, testContactSet);
        assertEquals(testContactManager.getFutureMeeting(2).getDate(), myDate);
        assertEquals(testContactManager.getFutureMeeting(2).getContacts(), testContactSet);
    }
}