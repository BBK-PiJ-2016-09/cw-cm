import java.util.Calendar;
import java.util.Set;
import java.util.*;

public class ContactManagerImpl {
    private Map<Integer, FutureMeeting> futureMeetings = new HashMap<Integer, FutureMeeting>();
    private Map<Integer, Contact> contacts = new HashMap<Integer, Contact>();

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
        return newContact.getId();
    }

}
