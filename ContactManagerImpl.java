import java.util.Calendar;
import java.util.Set;
import java.util.*;

public class ContactManagerImpl {
    private Map<Integer, FutureMeeting> futureMeetings = new HashMap<Integer, FutureMeeting>();
    private Map<Integer, Contact> contacts = new HashMap<Integer, Contact>();
    private Map<String, Integer> contactName2Id = new HashMap<String, Integer>();
    private Map<Integer, PastMeeting> pastMeetings = new HashMap<Integer, PastMeeting>();
    private boolean validateContacts(Set<Contact> contactsToValidate) {
        for (Contact contact : contactsToValidate) {
            if(contacts.get(contact.getId()) == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Add a new meeting to be held in the future.
     *
     * An ID is returned when the meeting is put into the system. This
     * ID must be positive and non-zero.
     *
     * @param contacts a set of contacts that will participate in the meeting
     * @param date the date on which the meeting will take place
     * @return the ID for the meeting
     * @throws IllegalArgumentException if the meeting is set for a time
     *       in the past, of if any contact is unknown / non-existent.
     * @throws NullPointerException if the meeting or the date are null
     */
    public int addFutureMeeting(Calendar date, Set<Contact> contacts) throws IllegalArgumentException {
        if(Calendar.getInstance().after(date)) {
            throw new IllegalArgumentException();
        }
        if(validateContacts(contacts)){
            FutureMeeting futureMeetingToBeAdded = new FutureMeetingImpl(date, contacts);
            futureMeetings.put(futureMeetingToBeAdded.getId(), futureMeetingToBeAdded);
            return futureMeetingToBeAdded.getId();

        }
        throw new IllegalArgumentException();


    }


    public FutureMeeting getFutureMeeting(int id) {
        return futureMeetings.get(id);
    }


    /**
     * Returns the meeting with the requested ID, or null if it there is none.
     *
     * @param id the ID for the meeting
     * @return the meeting with the requested ID, or null if it there is none.
     */
    public Meeting getMeeting(int id) {
        for (int meetingId: futureMeetings.keySet()) {
            if(meetingId == id) {
                return futureMeetings.get(meetingId);
            }

        }
        for (int meetingId: pastMeetings.keySet()) {
            if(meetingId == id){
                return pastMeetings.get(meetingId);
            }

        }
        return null;
    }


    /**
     * Create a new contact with the specified name and notes.
     *
     * @param name the name of the contact.
     * @param notes notes to be added about the contact.
     * @return the ID for the new contact
     * @throws IllegalArgumentException if the name or the notes are empty strings
     * @throws NullPointerException if the name or the notes are null
     */
    public int addNewContact(String name, String notes) throws NullPointerException, IllegalArgumentException {
        if(notes == null || name == null) {
            throw new NullPointerException(" Notes or name can not be null");

        }
        if(notes == "" || name == "") {
            throw new IllegalArgumentException();
        }
        Contact newContact = new ContactImpl(name);
        newContact.addNotes(notes);
        contacts.put(newContact.getId(), newContact);
        contactName2Id.put(name, newContact.getId());
        return newContact.getId();
    }

    /**
     * Returns a set containing the contacts that correspond to the IDs.
     * Note that this method can be used to retrieve just one contact by passing only one ID.
     *
     * @param ids an arbitrary number of contact IDs
     * @return a set containing the contacts that correspond to the IDs.
     * @throws IllegalArgumentException if no IDs are provided or if
     *     any of the provided IDs does not correspond to a real contact
     */
    public  Set<Contact> getContacts(int... ids) throws IllegalArgumentException{
        if(ids.length == 0) {
            throw new IllegalArgumentException("Contact ids are empty");
        }
        Set<Contact> setToReturn = new HashSet<Contact>();
        for(int i=0; i < ids.length;i++) {
            if(contacts.get(ids[i]) == null) {
                throw new IllegalArgumentException("Contact id not found " + ids[i]);
            }
            setToReturn.add(contacts.get(ids[i]));
        }
        return setToReturn;
    }

    /**
     * Returns a set with the contacts whose name contains that string.
     *
     * If the string is the empty string, this methods returns the set
     * that contains all current contacts.
     *
     * @param name the string to search for
     * @return a set with the contacts whose name contains that string.
     * @throws NullPointerException if the parameter is null
     */


    public Set<Contact> getContacts(String name) throws NullPointerException {
        if(name == "") {
            throw new NullPointerException("Contact name is empty");
        }
        Set<Contact> setToReturn = new HashSet<Contact>();
        for (int contactId: contacts.keySet()) {
            if(contacts.get(contactId).getName() != null){
                setToReturn.add(contacts.get(contactId));
            }
        }
        return setToReturn;
    }


    /**
     * Returns the list of future meetings scheduled with this contact.
     *
     * If there are none, the returned list will be empty. Otherwise,
     * the list will be chronologically sorted and will not contain any
     * duplicates.
     *
     * @param contact one of the user’s contacts
     * @return the list of future meeting(s) scheduled with this contact (maybe empty).
     * @throws IllegalArgumentException if the contact does not exist
     * @throws NullPointerException if the contact is null
     */
    public List<Meeting> getFutureMeetingList(Contact contact) throws IllegalArgumentException, NullPointerException {
        List<Meeting> meetingList = new ArrayList<Meeting>();
        if(contact == null) {
            throw new NullPointerException("Contact is null");
        }
        try {
            getContacts(contact.getId());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Contact not found when getting futureMeetingList, contact: ");
        }
        for (int meetingId: futureMeetings.keySet()) {
            futureMeetings.get(meetingId);
            if(futureMeetings.get(meetingId).getContacts().contains(contact)) {
                meetingList.add(getMeeting((meetingId)));
            }
        }
        return meetingList;

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
    public List<Meeting> getMeetingListOn(Calendar date) throws NullPointerException {
        throw new NullPointerException();
    }

}
