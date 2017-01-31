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
        FutureMeeting testMeeting = new FutureMeetingImpl(myDate, testContactSet);

        ContactManagerImpl testContactManager = new ContactManagerImpl();
        testContactManager.addFutureMeeting(testContactSet, myDate);

        assertEquals(testContactManager.addFutureMeeting(testContactSet, myDate), testMeeting.getId());
    }
}