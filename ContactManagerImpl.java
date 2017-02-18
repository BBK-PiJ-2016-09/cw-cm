import java.util.Calendar;
import java.util.Set;
import java.util.*;

public class ContactManagerImpl {
    private Map<Integer, FutureMeeting> futureMeetings = new HashMap<Integer, FutureMeeting>();
    private Map<Integer, Contact> contacts = new HashMap<Integer, Contact>();
    private Map<String, Integer> contactName2Id = new HashMap<String, Integer>();

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
        //if(contactsUnknown) {
             FutureMeeting futureMeetingToBeAdded = new FutureMeetingImpl(date, contacts);
                futureMeetings.put(futureMeetingToBeAdded.getId(), futureMeetingToBeAdded);
                return futureMeetingToBeAdded.getId();
        //   }
    //}
        }
        throw new IllegalArgumentException();


    }


    public FutureMeeting getFutureMeeting(int id) {
        return futureMeetings.get(id);
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
            throw new NullPointerException();
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
            if(contacts.get(ids[i]) == null){
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
          return new HashSet<Contact>();
//        Set<Contact> setToReturn = new HashSet<Contact>;


     }


}
