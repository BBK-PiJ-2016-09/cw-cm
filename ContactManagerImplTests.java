import java.util.Calendar;
import java.util.Set;
import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class ContactManagerImplTests {
    Calendar currentDate = Calendar.getInstance();
    Contact testContact = new ContactImpl("contactName");

    Set<Contact> testContactSet = new HashSet<Contact>();
    ContactManagerImpl testContactManager = new ContactManagerImpl();
    int testContactId = testContactManager.addNewContact("a contact name", "some contact notes");
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
    public void addFutureMeetingInThePastTest() {
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
    @Test
    public void getFutureMeetingTest() {
        currentDate.add(Calendar.DATE, 15);
        Contact testContact = new ContactImpl("contactName");
        assertEquals(testContactManager.addFutureMeeting(currentDate, testContactSet), futureMeetingToBeAdded.getId() + 1);
        Integer meetingId = testContactManager.addFutureMeeting(currentDate, testContactSet);
    }

    @Test(expected=IllegalArgumentException.class)
    public void addNewContactWithoutName() {
        Integer testContactId = testContactManager.addNewContact("", "Some notes");
    }

    @Test(expected=IllegalArgumentException.class)
    public void addNewContactWithoutNotes() {
        Integer testContactId = testContactManager.addNewContact("Contact name", "");
    }
    @Test(expected=IllegalArgumentException.class)
    public void addNewContactWithoutNameNorNotes() {
        Integer testContactId = testContactManager.addNewContact("", "");
    }

    @Test(expected=IllegalArgumentException.class)
    public void getContactsEmptyIdListTest() {
        testContactManager.getContacts(new int [0]);
    }

    @Test(expected=IllegalArgumentException.class)
    public void getContactsIdsNotFoundTest() {
        int [] a ={4,5,6};
        testContactManager.getContacts(a);
    }


    @Test
    public void getContactsByIDTest() {

        Set<Contact> setToCompare = testContactManager.getContacts(testContactId);
        Contact found = null;
        for (Contact i:setToCompare) {
            if(i.getId() == testContactId){
                found = i;
            }

        }
        assertEquals("a contact name",found.getName());

    }

    @Test
    public void getContactsByNameTest() {
        Set<Contact> setToCompare = testContactManager.getContacts("contact");
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
        Set<Contact> setToCompare = testContactManager.getContacts("");
    }

    /*@Test
    public void addNewContactTest() {
        Integer testContactId = testContactManager.addNewContact("a name", "notes");
        assertEquals("a name", testContactManager.getContacts(testContactId));
    }*/



    @Test(expected=NullPointerException.class)
    public void addNewContactWithNullName() {
        Integer testContactId = testContactManager.addNewContact(null, "notes");
    }

    @Test(expected=NullPointerException.class)
    public void addNewContactWithNullNotesTest() {
        Integer testContactId = testContactManager.addNewContact("a name", null);
    }


    @Test(expected=NullPointerException.class)
    public void addNewContactWithNullNameAndNotesTest() {
        Integer testContactId = testContactManager.addNewContact(null, null);
    }

    /**
     * Returns the meeting with the requested ID, or null if it there is none.
     *
     * @param id the ID for the meeting
     * @return the meeting with the requested ID, or null if it there is none.
     */
    @Test
    public void getMeetingTest() {
        Meeting testMeeting = testContactManager.getMeeting(futureMeetingToBeAdded.getId());
        assertEquals(testMeeting, futureMeetingToBeAdded);
    }
}