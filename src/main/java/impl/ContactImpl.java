package impl;

import java.io.Serializable;
import specs.Contact;

/**
 * A contact is a person we are making business with or may do in the future.
 * <p></p>
 * Contacts have an ID (unique, a non-zero positive integer),
 * a name (not necessarily unique), and notes that the user
 * may want to save about them.
 */

public final class ContactImpl implements Contact, Serializable {

  /**
    * The name of the contact.
    */
  private String contactName;
  /**
    * The id of the contact.
    */
  private int contactId;
  /**
    * The total amount of contacts ever created.
    */
  private static int totalContacts = 0;
  /**
    * The notes associated to the contact.
    */
  private String contactNotes = "";


  /**
   * Get total contacts.
   *
   * @return the total number of contacts.
  */
  public int getTotalContacts() {
    return totalContacts;
  }

  /**
   * Set the value of total contacts.
   * @param newValue the new value to assign
  */
  public void setTotalContact(final int newValue) {
    totalContacts = newValue;
  }

  /**
   * Constructor.
   *  @param name (requires) name of the contact
   *  @author Enric Serra
   *  @version 1.0
   */
  public ContactImpl(final String name) {
    setTotalContact(getTotalContacts() + 1);
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
    * <p></p>
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
  public void addNotes(final String note) {
    if (contactNotes == "") {
      contactNotes = note;
    } else {
      contactNotes += "\n" + note;
    }
  }
}
