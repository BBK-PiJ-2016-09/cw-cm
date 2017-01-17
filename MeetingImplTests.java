import org.junit.*;
import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.Set;
import java.util.*;

public class MeetingImplTests {

    Calendar myDate = Calendar.getInstance();
    Meeting testMeeting = new MeetingImpl(myDate);
    Contact testContact = new ContactImpl("contactName");
    Set<Contact> testContactSet = new HashSet<>();



    @Test
    public void getMeetingIdTest() {
        assertEquals(testMeeting.getId(), 3);
    }

    @Test
    public void getDateTest() {
        assertEquals(testMeeting.getDate(), myDate);
    }
    @Test
    public void getContactsTest() {
        testContactSet.add(testContact);
        assertEquals(testMeeting.getContacts(), testContactSet);
    }
}
