/**
 * A contact is a person we are making business with or may do in the future.
 *
 * Contacts have an ID (unique, a non-zero positive integer),
 * a name (not necessarily unique), and notes that the user
 * may want to save about them.
 */
public class ContactImpl implements Contact {
    private String contactName;
    private int contactId;
    public static int totalContacts = 0;

    private String contactNotes = "";

    public ContactImpl(String name) {
        totalContacts ++;
        this.contactName = name;
        this.contactId = totalContacts;

    }

    /**
     * Returns the ID of the contact.
     *
     * @return the ID of the contact.
     */

    public int getId() {
        return contactId;
    }

    /**
     * Returns the name of the contact.
     *
     * @return the name of the contact.
     */
    public String getName() {
        return this.contactName;
    }

    /**
     * Returns our notes about the contact, if any.
     *
     * If we have not written anything about the contact, the empty
     * string is returned.
     *
     * @return a string with notes about the contact, maybe empty.
     */
    public String getNotes() {
        return this.contactNotes;
    }

    /**
     * Add notes about the contact.
     *
     * @param note the notes to be added
     */
    public void addNotes(String note) {
        contactNotes = note;

    }
}
