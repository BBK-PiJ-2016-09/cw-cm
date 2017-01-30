import org.junit.*;
import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.Set;
import java.util.*;

public class MeetingImplTests {

    Calendar myDate = Calendar.getInstance();
    Contact testContact = new ContactImpl("contactName");
    Set<Contact> testContactSet = new HashSet<>();
    PastMeeting testMeeting = new PastMeetingImpl(myDate, testContactSet, "Some notes about the meeting");

    @Test
    public void getMeetingIdTest() {
        assertEquals(testMeeting.getId(), 4);
    }

    @Test
    public void getDateTest() {
        assertEquals(testMeeting.getDate(), myDate);
    }
    @Test
    public void getContactsTest() {

        testContactSet.add(testContact);
        Meeting meetingWithContacts = new MeetingImpl(myDate, testContactSet);
        assertEquals(testMeeting.getContacts(), testContactSet);
    }
    @Test
    public void getDateTest() {
        assertEquals(testMeeting.getDate(), myDate);
    }

    @Test
    public Contact getNotesTest() {
        assertEquals(testMeeting.getNotes(), "Some notes about the meeting");
    }
}
