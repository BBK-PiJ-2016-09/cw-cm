import java.util.Calendar;
import java.util.Set;
import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;

public class ContactManagerImplTests {
    private Calendar currentDate;
    private Calendar futureDate;
    private Calendar pastDate;

    private ContactManagerImpl testContactManager;
    private Contact testContact;

    private Set<Contact> testContactSet;
    private int testContactId;
    private int futureMeetingToBeAddedId;
    private int pastMeetingToBeAddedId;

    @Before
    public void initialize() {

        currentDate = Calendar.getInstance();
        futureDate = Calendar.getInstance();
        futureDate.add(Calendar.DATE, 15);
        pastDate = Calendar.getInstance();
        pastDate.add(Calendar.DATE, -15);

        testContactManager = new ContactManagerImpl();
        testContactId = testContactManager.addNewContact("a contact name", "some contact notes");
        testContactSet = testContactManager.getContacts(testContactId);


        Iterator<Contact> iterator = testContactSet.iterator();
        testContact = iterator.next();

        futureMeetingToBeAddedId = testContactManager.addFutureMeeting(testContactSet, futureDate);
        pastMeetingToBeAddedId = testContactManager.addNewPastMeeting(testContactSet, pastDate, "");

    }




    @Test
    public void getContactsByIDTest() {
        Set<Contact> setToCompare = testContactManager.getContacts(testContactId);
        Contact found = null;
        for (Contact i:setToCompare) {
            if(i.getId() == testContactId) {
                found = i;
            }
        }
        assertEquals("a contact name", found.getName());
    }

    @Test
    public void getContactsByNameTest() {
        Set<Contact> setToCompare = testContactManager.getContacts("a contact");
        Contact found = null;
        for (Contact i:setToCompare) {
            if(i.getId() == testContactId) {
                found = i;
            }
        }
        assertEquals("a contact name", found.getName());
    }

    @Test(expected=NullPointerException.class)
    public void getContactsByNameWhenNameIsEmptyTest() {
        String argument = null;
        Set<Contact> setToCompare = testContactManager.getContacts(argument);
    }


    @Test(expected = NullPointerException.class)
    public void addNewContactWithNullName() {
        int integerForTest1 = testContactManager.addNewContact(null, "notes");
    }

    @Test(expected = NullPointerException.class)
    public void addNewContactWithNullNotesTest() {
        int integerForTest2 = testContactManager.addNewContact("a name", null);
    }


    @Test(expected = NullPointerException.class)
    public void addNewContactWithNullNameAndNotesTest() {
        int integerForTest3 = testContactManager.addNewContact(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addFutureMeetingInThePastTest() {
        int meetingId = testContactManager.addFutureMeeting(testContactSet, pastDate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNewContactWithoutName() {
        int testContactId = testContactManager.addNewContact("", "Some notes");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNewContactWithoutNotes() {
        int testContactId = testContactManager.addNewContact("Contact name", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNewContactWithoutNameNorNotes() {
        Integer testContactId = testContactManager.addNewContact("", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getContactsEmptyIdListTest() {
        int [] b = new int[0];
        testContactManager.getContacts(b);
    }


    @Test
    public void getUnexistentMeetingTest() {
        assertEquals(null, testContactManager.getMeeting(312312431));
    }

    @Test
    public void getExistentMeetingTest() {
        assertEquals(futureMeetingToBeAddedId, testContactManager.getMeeting(futureMeetingToBeAddedId).getId());
    }

    @Test
    public void getMeetingList() {
        assertEquals(futureMeetingToBeAddedId, testContactManager.getMeeting(futureMeetingToBeAddedId).getId());
    }

    @Test(expected = NullPointerException.class)
    public void getFutureMeetingWithNullContactTest() {
        testContactManager.getFutureMeetingList(null);
    }


    @Test(expected = NullPointerException.class)
    public void getMeetingListOnNullDateTest() {
        testContactManager.getMeetingListOn(null);
    }










    @Test
    public void getPastMeetingListForTest() {
        assertEquals(testContactManager.getPastMeetingListFor(testContact).get(0).getId(), pastMeetingToBeAddedId);
    }

    @Test
    public void addFutureMeetingTest() {
        assertEquals(testContactManager.addFutureMeeting(testContactSet, futureDate), futureMeetingToBeAddedId + 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getContactsIdsNotFoundTest() {
        int [] b = {4,5,232424};
        testContactManager.getContacts(b);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getFutureMeetingWithInexistentContactTest() {
        Contact unRegisteredContact = new ContactImpl("ContactName");
        testContactManager.getFutureMeetingList(unRegisteredContact);
    }

    @Test
    public void getMeetingListONDateTest() {
        assertEquals(testContactManager.getMeetingListOn(futureDate).get(0).getId(), futureMeetingToBeAddedId) ;
    }

    @Test
    public void getFutureMeetingTest() {
        int futureMeetingToBeAddedId = testContactManager.addFutureMeeting(testContactSet, futureDate);
        assertEquals(testContactManager.addFutureMeeting(testContactSet, futureDate), futureMeetingToBeAddedId + 1);
        Integer meetingId = testContactManager.addFutureMeeting(testContactSet, futureDate);
    }

    @Test
    public void getFutureMeetingListTest() {

        assertEquals(testContactManager.getFutureMeetingList(testContact).get(0).getId(), futureMeetingToBeAddedId);
    }

    @Test
    public void addPastMeetingTest() {
        assertEquals(testContactManager.addNewPastMeeting(testContactSet, pastDate, ""), pastMeetingToBeAddedId + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addMeetingNotesIdNotFoundTest() {
        testContactManager.addMeetingNotes(432423423, "NOTES");
    }

    @Test(expected = NullPointerException.class)
    public void addMeetingNotesNullTextTest() {
        testContactManager.addMeetingNotes(pastMeetingToBeAddedId, null);
    }

    @Test(expected = IllegalStateException.class)
    public void addMeetingNotesToFutureMeetingTest() {
        testContactManager.addMeetingNotes(futureMeetingToBeAddedId, "NOTES");
    }

    @Test
    public void addMeetingNotesTest() {
        Calendar rightNowDate = Calendar.getInstance();
        rightNowDate.add(Calendar.SECOND, 1);
        int aReallyImminentMeetingId = testContactManager.addFutureMeeting(testContactSet, rightNowDate);
        try {
            Thread.sleep(2000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        PastMeeting MeetingInThePast = testContactManager.addMeetingNotes(aReallyImminentMeetingId, "NOTES");
        assertEquals(MeetingInThePast.getDate(), rightNowDate);
        assertEquals(MeetingInThePast.getNotes(), "NOTES");
        assertEquals(MeetingInThePast.getId(), aReallyImminentMeetingId + 1);
        assertEquals(testContactManager.getMeetingListOn(rightNowDate).get(0), MeetingInThePast);
    }


}
