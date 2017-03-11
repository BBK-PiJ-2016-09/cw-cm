package test;

import impl.ContactImpl;
import impl.MeetingImpl;
import impl.PastMeetingImpl;
import specs.Contact;
import specs.Meeting;
import specs.PastMeeting;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.Set;
import java.util.*;

public class PastMeetingImplTests {

    Calendar myDate = Calendar.getInstance();
    Contact testContact = new ContactImpl("contactName");
    Set<Contact> testContactSet = new HashSet<Contact>();
    PastMeeting testMeeting = new PastMeetingImpl(myDate, testContactSet, "Some notes about the meeting");

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
    public void getNotesNotEmptyTest() {
        assertEquals(testMeeting.getNotes(), "Some notes about the meeting");
    }


    @Test
    public void getEmptyNotesTest() {
        assertEquals((new PastMeetingImpl(myDate, testContactSet, "")).getNotes(), "");
    }

    @Test
    public void addNotesTest() {
        PastMeetingImpl newMeeting = new PastMeetingImpl(myDate, testContactSet, "Some notes about the meeting");
        newMeeting.addNotes("Some additional notes");
        assertEquals(newMeeting.getNotes(), "Some notes about the meeting\nSome additional notes");
    }


}
