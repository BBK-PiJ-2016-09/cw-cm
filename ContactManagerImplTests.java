import java.util.Calendar;
import java.util.Set;
import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class ContactManagerImplTests {
    private Calendar currentDate;
    private Contact testContact;
    private Set<Contact> testContactSet;
    private ContactManagerImpl testContactManager;
    private int testContactId;
    private int futureMeetingToBeAddedId;

    @Before
    public void initialize() {
        currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DATE, 15);
        testContact = new ContactImpl("contactName");
        testContactSet = new HashSet<Contact>();
        testContactManager = new ContactManagerImpl();
        testContactId = testContactManager.addNewContact("a contact name", "some contact notes");
        futureMeetingToBeAddedId = testContactManager.addFutureMeeting(currentDate, testContactSet);

    }

    @Test
    public void addFutureMeetingTest() {
        assertEquals(testContactManager.addFutureMeeting(currentDate, testContactSet), futureMeetingToBeAddedId + 1);
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

    @Test
    public void getFutureMeetingTest() {
        currentDate.add(Calendar.DATE, 15);
        Contact testContact = new ContactImpl("contactName");
        assertEquals(testContactManager.addFutureMeeting(currentDate, testContactSet), futureMeetingToBeAddedId + 1);
        Integer meetingId = testContactManager.addFutureMeeting(currentDate, testContactSet);
    }

    @Test(expected=NullPointerException.class)
    public void getContactsByNameWhenNameIsEmptyTest() {
        Set<Contact> setToCompare = testContactManager.getContacts("");
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
        currentDate.add(Calendar.DATE, -30);
        assertEquals(testContactManager.addFutureMeeting(currentDate, testContactSet), futureMeetingToBeAddedId + 1);
        Integer meetingId = testContactManager.addFutureMeeting(currentDate, testContactSet);
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

    @Test(expected = IllegalArgumentException.class)
    public void getContactsIdsNotFoundTest() {
        int [] b ={4,5,6};
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

    @Test(expected = IllegalArgumentException.class)
    public void getFutureMeetingWithInexistentContactTest() {
        Contact unRegisteredContact = new ContactImpl("ContactName");
        testContactManager.getFutureMeetingList(unRegisteredContact);
    }

    @Test(expected = NullPointerException.class)
    public void getFutureMeetingWithNullContactTest() {
        testContactManager.getFutureMeetingList(null);
    }

    @Test
    public void getFutureMeetingList() {
        Set<Contact> registeredContactSet = testContactManager.getContacts(testContactId);
        Contact registeredContact = null;
        for (Contact contact: registeredContactSet) {
            if(contact.getId() == testContactId){
                registeredContact = contact;
            }
        }
        int futureMeetingForThisTestId = testContactManager.addFutureMeeting(currentDate, registeredContactSet);
        assertEquals(testContactManager.getFutureMeetingList(registeredContact).get(0).getId(), futureMeetingForThisTestId);
    }

    /**
     * Returns the list of meetings that are scheduled for, or that took
     * place on, the specified date
     *
     * If there are none, the returned list will be empty. Otherwise,
     * the list will be chronologically sorted and will not contain any
     * duplicates.
     *
     * @param date the date
     * @return the list of meetings
     * @throws NullPointerException if the date are null
     */
    @Test
    public void getMeetingListONDateTest() {
        Set<Contact> registeredContactSet = testContactManager.getContacts(testContactId);

        int futureMeetingForThisTestId = testContactManager.addFutureMeeting(currentDate, registeredContactSet);
        assertEquals(testContactManager.getMeetingListOn(currentDate).get(0), testContactManager.getMeeting(futureMeetingForThisTestId)) ;
    }

    @Test(expected = NullPointerException.class)
    public void getMeetingListONNullDateTest() {
        testContactManager.getMeetingListOn(null);
    }



}