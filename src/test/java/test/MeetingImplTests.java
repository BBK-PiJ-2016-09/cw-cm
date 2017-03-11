package test;

import impl.ContactImpl;
import impl.MeetingImpl;
import specs.Contact;
import specs.Meeting;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.Set;
import java.util.*;

public class MeetingImplTests {

    Calendar myDate = Calendar.getInstance();
    Contact testContact = new ContactImpl("contactName");
    Set<Contact> testContactSet = new HashSet<Contact>();
    Meeting testMeeting = new MeetingImpl(myDate, testContactSet);

    @Test
    public void getMeetingIdTest() {
        assertEquals(testMeeting.getId(), 4);
    }

    @Test
    public void getDateTest() {
        assertEquals(testMeeting.getDate(), myDate);
    }
    @Test
    public void addNewContactTest() {

        testContactSet.add(testContact);
        Meeting meetingWithContacts = new MeetingImpl(myDate, testContactSet);
        assertEquals(testMeeting.getContacts(), testContactSet);
    }
}
