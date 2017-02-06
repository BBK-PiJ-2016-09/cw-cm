import java.util.Calendar;
import java.util.Set;
import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class ContactManagerImplTests {
    Calendar currentDate = Calendar.getInstance();
    Contact testContact = new ContactImpl("contactName");

    Set<Contact> testContactSet = new HashSet<>();
    ContactManagerImpl testContactManager = new ContactManagerImpl();
    FutureMeeting futureMeetingToBeAdded = new FutureMeetingImpl(currentDate, testContactSet);

    @Test
    public void addFutureMeetingTest() {
        currentDate.add(Calendar.DATE, 15);
        Contact testContact = new ContactImpl("contactName");
        assertEquals(testContactManager.addFutureMeeting(currentDate, testContactSet), futureMeetingToBeAdded.getId() + 1);
    }

    @Test(expected=IllegalArgumentException.class)
    /**
     *  If we create a future meeting with past date it will throw an exception
     */
    public void getFutureMeetingTest() {
        currentDate.add(Calendar.DATE, -15);
        Contact testContact = new ContactImpl("contactName");
        assertEquals(testContactManager.addFutureMeeting(currentDate, testContactSet), futureMeetingToBeAdded.getId() + 1);
        Integer meetingId = testContactManager.addFutureMeeting(currentDate, testContactSet);
    }
    /*@Test
    public void addNewPastMeetingTest(){
        testContactManager.addNewPastMeeting(testContact, currentDate, )
        Contact testContact = new ContactImpl("contactName");
        assertEquals(testContactManager.addFutureMeeting(currentDate, testContactSet), futureMeetingToBeAdded.getId() + 1);
    }
    @Test
    public void addNewPastMeetingTest(){
        testContactManager.addNewPastMeeting(testContact, currentDate);
        Contact testContact = new ContactImpl("contactName");
        assertEquals(testContactManager.addFutureMeeting(currentDate, testContactSet), futureMeetingToBeAdded.getId() + 1);
    }*/
}