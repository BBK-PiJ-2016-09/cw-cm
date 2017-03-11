package impl;

import specs.Contact;
import specs.FutureMeeting;
import specs.Meeting;
import specs.PastMeeting;

import java.io.*;
import java.util.*;


/**
 * A contact manager.
 * It keeps track of contacts and meetings.
 */
public class ContactManagerImpl {

  /**
   * All the meetings.
   */
  public Map<Integer, Meeting> meetings = new HashMap<Integer, Meeting>();

  /**
   * All the contacts.
   */
  private Map<Integer, Contact> contacts = new HashMap<Integer, Contact>();

  /**
   * The name of the file to save our data.
   */
  private final String saveFileName = "defaultSaveFile.txt";

  /**
   * A number needed for serializable for consistency.
   */
  private static final long serialVersionUID = 33L;

  /**
   * Constructor.
   *  @author Enric Serra
   *  @version 1.0
   */
  public ContactManagerImpl() {
      super();
      ObjectInputStream inputFile = null;
      try {
          inputFile = new ObjectInputStream(new FileInputStream(saveFileName));
          meetings = (HashMap<Integer, Meeting>) inputFile.readObject();
          contacts = (HashMap<Integer, Contact>) inputFile.readObject();
      } catch (FileNotFoundException ex) {
          ex.printStackTrace();
      } catch (IOException ex) {
          ex.printStackTrace();
      } catch (ClassNotFoundException ex) {
          ex.printStackTrace();
      } finally {
          if (inputFile != null) {
              try {
                  inputFile.close();
              } catch (IOException ex) {
                  ex.printStackTrace();
              }
          }
      }
  }

  /**
   * Validate a contact.
   *
   * @param contact the contact to be validated.
   * @return boolean, whether the contact is a valid one
   * strings
   */
  private boolean validateContact(final Contact contact) {
    return (contacts.get(contact.getId()) != null);
  }

  /**
   * Validate a set of contacts.
   *
   * @param contactsToValidate the set of contacts to be validated.
   * @return boolean, whether the set is full of valid contacts
   * strings
   */
  private boolean validateContacts(final Set<Contact>
                                           contactsToValidate) {
    Iterator<Contact> iterator = contactsToValidate.iterator();
    while (iterator.hasNext()) {
      if (!validateContact(iterator.next())) {
        return false;
      }
    }
    return true;
  }

  /**
   * Validate a collection of contacts.
   *
   * @param contactsToValidate the collection of contacts to be validated.
   * @return boolean, whether the collection is full of valid contacts
   * strings
   */
  private boolean validateContacts(final Collection<Contact>
                                           contactsToValidate) {
    Set<Contact> contactsSet = new HashSet<Contact>(contactsToValidate);
    return validateContacts(contactsSet);
  }


  /**
    * Create a new contact with the specified name and notes.
    *
    * @param name the name of the contact.
    * @param notes notes to be added about the contact.
    * @return the ID for the new contact
    * @throws IllegalArgumentException if the name or the notes are empty
    * strings
    * @throws NullPointerException if the name or the notes are null
    */
  public int addNewContact(final String name, final String notes) throws
          NullPointerException, IllegalArgumentException {
    if (notes == null || name == null) {
      throw new NullPointerException(" Notes or name can not be null");
    }
    if (notes == "" || name == "") {
      throw new IllegalArgumentException();
    } else {
      Contact newContact = new ContactImpl(name);
      newContact.addNotes(notes);
      contacts.put(newContact.getId(), newContact);
      return newContact.getId();
    }
  }

  /**
    * Add a new meeting to be held in the future.
    * An ID is returned when the meeting is put into the system. This
    * ID must be positive and non-zero.
    *
    * @param contactsForThisMeeting a set of contacts that will participate
    *                               in the meeting
    * @param date the date on which the meeting will take place
    * @return the ID for the meeting
    * @throws IllegalArgumentException if the meeting is set for a time
    *       in the past, of if any contact is unknown / non-existent.
    * @throws NullPointerException if the meeting or the date are null
    */
  public int addFutureMeeting(final Set<Contact> contactsForThisMeeting,
                              final Calendar date)
          throws IllegalArgumentException {
    if (Calendar.getInstance().after(date)) {
      throw new IllegalArgumentException("Trying to add a future meeting in "
              + "the past");
    }
    if (contactsForThisMeeting == null) {
      throw new IllegalArgumentException("Could not validate contacts");
    }
    if (date == null) {
      throw new NullPointerException("Date is null");
    }
    if (!validateContacts(contactsForThisMeeting)) {
      throw new IllegalArgumentException("Could not validate contacts");
    } else {
      FutureMeeting futureMeetingToBeAdded = new FutureMeetingImpl(date,
              contactsForThisMeeting);
      meetings.put(futureMeetingToBeAdded.getId(), futureMeetingToBeAdded);
      return futureMeetingToBeAdded.getId();
    }
  }

  /**
   * Returns the FutureMeeting with the requested ID, or null if it there is
   * none.
   *
   * @param id the ID for the meeting
   * @return the meeting with the requested ID, or null if there is none.
   */
  public FutureMeeting getFutureMeeting(final int id) {
    return (FutureMeeting) meetings.get(id);
  }

  /**
    * Returns the meeting with the requested ID, or null if it there is none.
    *
    * @param id the ID for the meeting
    * @return the meeting with the requested ID, or null if it there is none.
    */
  public Meeting getMeeting(final int id) {
    return meetings.get(id);
  }

  /**
    * Returns a set containing the contacts that correspond to the IDs.
    * Note that this method can be used to retrieve just one contact by
    * passing only one ID.
    *
    * @param ids an arbitrary number of contact IDs
    * @return a set containing the contacts that correspond to the IDs.
    * @throws IllegalArgumentException if no IDs are provided or if
    *     any of the provided IDs does not correspond to a real contact
    */
  public  Set<Contact> getContacts(final int... ids)
          throws IllegalArgumentException {
    if (ids.length == 0) {
      throw new IllegalArgumentException("Contact ids are empty");
    } else {
      Set<Contact> setToReturn = new HashSet<Contact>();
      for (int i : ids) {
        setToReturn.add(contacts.get(i));
        if (contacts.get(i) == null) {
          throw new IllegalArgumentException("Contact not found");
        }
      }
      return setToReturn;
    }
  }

  /**
   * Returns a set with the contacts whose name contains that string.
   * If the string is the empty string, this methods returns the set
   * that contains all current contacts.
   *
   * @param name the string to search for
   * @return a set with the contacts whose name contains that string.
   * @throws NullPointerException if the parameter is null
   */
  public Set<Contact> getContacts(final String name)
          throws NullPointerException {
    if (name == null) {
      throw new NullPointerException("Contact name is empty");
    }
    Set<Contact> setToReturn = new HashSet<Contact>();
    for (int contactId: contacts.keySet()) {
      if (contacts.get(contactId).getName() != null) {
        setToReturn.add(contacts.get(contactId));
      }
    }
    return setToReturn;
  }

  /**
   * Returns the list of past meetings in which this contact has participated.
   * If there are none, the returned list will be empty. Otherwise,
   * the list will be chronologically sorted and will not contain any
   * duplicates.
   *
   * @param contact one of the user’s contacts
   * @return the list of Past meeting(s) scheduled with this contact
   * (maybe empty).
   * @throws IllegalArgumentException if the contact does not exist
   * @throws NullPointerException if the contact is null
   */
  public List<PastMeeting> getPastMeetingListFor(final Contact contact) throws
          NullPointerException, IllegalArgumentException {
    if (contact == null) {
      throw new NullPointerException("Contact is null ");
    }
    if (contacts.get(contact.getId()) == null) {
      throw new IllegalArgumentException("Contact is null ");
    } else {
      List<Meeting> meetingsToFilter = getMeetings(contact);
      List<PastMeeting> meetingsToReturn = new ArrayList<PastMeeting>();
      for (Meeting meetingToFilter : meetingsToFilter) {
        if (!(meetingToFilter instanceof FutureMeeting)) {
          meetingsToReturn.add((PastMeeting) meetingToFilter);
        }
      }
      return meetingsToReturn;
    }
  }

  /**
   * Returns the list of future meetings scheduled with this contact.
   * If there are none, the returned list will be empty. Otherwise,
   * the list will be chronologically sorted and will not contain any
   * duplicates.
   *
   * @param contact one of the user’s contacts
   * @return the list of future meeting(s) scheduled
   * with this contact (maybe empty).
   * @throws IllegalArgumentException if the contact does not exist
   * @throws NullPointerException if the contact is null
   */
  public List<Meeting> getFutureMeetingList(final Contact contact) throws
          IllegalArgumentException, NullPointerException {
    List<Meeting> meetingList = new ArrayList<Meeting>();
    if (contact == null) {
      throw new NullPointerException("Contact is null");
    }
    try {
      getContacts(contact.getId());
    } catch (IllegalArgumentException ex) {
      throw new IllegalArgumentException("Contact not found when getting "
               + "futureMeetingList, contact: ");
    }
    List<Meeting> meetingsToFilter = getMeetings(contact);
    List<Meeting> meetingsToReturn = new ArrayList<Meeting>();
    for (Meeting meetingToFilter : meetingsToFilter) {
      if (meetingToFilter instanceof FutureMeeting) {
        meetingsToReturn.add((Meeting) meetingToFilter);
      }
    }
    return meetingsToReturn;
  }

  /**
   * Returns the list of meetings that took place on a date.
   * If there are none, the returned list will be empty.
   *
   * @param date the date
   * @return the list of meetings
   */
  private List<Meeting> getMeetings(final Calendar date) {
    List<Meeting> meetingsToReturn = new ArrayList<Meeting>();
    for (Meeting meeting: meetings.values()) {
      if (meeting.getDate() == date) {
        meetingsToReturn.add(meeting);
      }
    }
    return sortByDate(meetingsToReturn);
  }

  /**
   * Returns a list of meetings sorted by date.
   *
   * @param meetingsTosort the list of meetings to sort
   * @return The list of meetings sorted by date.
   */
  private List<Meeting> sortByDate(final List<Meeting> meetingsToSort) {
    for (int i=1;i < meetingsToSort.size(); i++) {
      for (int j = meetingsToSort.size() - 1; j >= i; j--) {
        if (isPreviousMeeting(meetingsToSort.get(i), meetingsToSort.get(j))) {
          swapMeetings(i, j, meetingsToSort);
        }
      }
    }
    return meetingsToSort;
  }

  /**
   * Checks whether the meeting with index 1 happened before the meeting
   * with index 2.
   * <p></p>
   * @param meeting1 the first meeting to compare.
   * @param meeting2 the second meeting to compare
   * @return the list of meetings
   */
  private boolean isPreviousMeeting(final Meeting meeting1,
                                    final Meeting meeting2) {
    return meeting1.getDate()
            .before(meeting2.getDate());
  }

  /**
   * Swaps 2 meetings in a list.
   * <p></p>
   * @param index1 the first meeting index in the list
   * @param index2 the second meeting index in the list
   * @param meetingList the List containing the meetings
   * @return the list of meetings
   */
  private List<Meeting> swapMeetings(final int index1,
                                     final int index2,
                                     final List<Meeting> meetingList) {
    Meeting helper = meetingList.get(index1);
    meetingList.set(index1, meetingList.get(index2));
    meetingList.set(index2, helper);
    return meetingList;
  }
  /**
   * Returns the list of meetings that were attended by contact.
   * If there are none, the returned list will be empty.
   *
   * @param contact the contact we want to retrieve meetings for
   * @return the list of meetings
   */
  private List<Meeting> getMeetings(final Contact contact) {
    List<Meeting> meetingsToReturn = new ArrayList<Meeting>();
    for (Meeting meeting: meetings.values()) {
      if (meeting.getContacts().contains(contact)) {
        meetingsToReturn.add(meeting);
      }
    }
    return sortByDate(meetingsToReturn);
  }

  /**
   * Returns the list of meetings that are scheduled for, or that took
   * place on, the specified date
   * If there are none, the returned list will be empty. Otherwise,
   * the list will be chronologically sorted and will not contain any
   * duplicates.
   *
   * @param date the date
   * @return the list of meetings
   * @throws NullPointerException if the date are null
   */
  public List<Meeting> getMeetingListOn(final Calendar date) throws
          NullPointerException {
    if (date == null) {
      throw new NullPointerException("Date for getMeetingListOn is null");
    } else {
      return getMeetings(date);
    }
  }

  /**
   * Create a new record for a meeting that took place in the past.
   *
   * @param contactsForMeeting a set of participants
   * @param date the date on which the meeting took place
   * @param text messages to be added about the meeting.
   * @return the ID for the meeting
   * @throws IllegalArgumentException if the list of contacts is
   *     empty, if any of the contacts does not exist, or if
   *     the date provided is in the future
   * @throws NullPointerException if any of the arguments is null
   */
  public int addNewPastMeeting(final Set<Contact> contactsForMeeting,
                               final Calendar date, final String text)
          throws IllegalArgumentException, NullPointerException {
    if (!Calendar.getInstance().after(date)) {
      throw new IllegalArgumentException("Date for past meeting is "
              + "NOT in the past");
    }
    if (contactsForMeeting.size() == 0) {
      throw new IllegalArgumentException("Contact set has no elements");
    }
    if (!validateContacts(contactsForMeeting)) {
      throw new IllegalArgumentException("Contacts are not valid");
    } else {
      PastMeeting pastMeetingToBeAdded = new PastMeetingImpl(date,
              contactsForMeeting, text);
      meetings.put(pastMeetingToBeAdded.getId(), pastMeetingToBeAdded);
      return pastMeetingToBeAdded.getId();
    }
  }

  /**
   * Add notes to a meeting.
   * This method is used when a future meeting takes place, and is
   * then converted to a past meeting (with notes) and returned.
   * It can be also used to add notes to a past meeting at a later date.
   *
   * @param id the ID of the meeting
   * @param text messages to be added about the meeting.
   * @throws IllegalArgumentException if the meeting does not exist
   * @throws IllegalStateException if the meeting is set for a date in the
   * future
   * @throws NullPointerException if the notes are null
   * @return the meeting to which notes were added
   */
  public PastMeeting addMeetingNotes(final int id, final String text) throws
          IllegalArgumentException, IllegalStateException,
          NullPointerException {
    if (text == null) {
      throw new NullPointerException("Meeting notes are null");
    }
    Meeting meeting = getMeeting(id);
    PastMeetingImpl newMeeting;
    if (meeting == null) {
      throw new IllegalArgumentException("Meeting not found");
    }
    if (!Calendar.getInstance().after(meeting.getDate())) {
      throw new IllegalStateException("Meeting has not yet taken place");
    }
    if (meeting instanceof PastMeeting) {
      newMeeting = (PastMeetingImpl) meeting;
      newMeeting.addNotes(text);
    }
    if (meeting instanceof FutureMeeting) {
      int newMeetingId = addNewPastMeeting(meeting.getContacts(),
              meeting.getDate(), text);
      newMeeting = (PastMeetingImpl) getMeeting(newMeetingId);
      meetings.remove(id);
    } else {
      throw new IllegalArgumentException("Meeting is not of type"
              + " FutureMeeting or PastMeeting");
    }
    meeting = newMeeting;
    return newMeeting;
  }

  /**
   * Saves the schedule to a file.
   *
   */
  void flush() {
    ObjectOutputStream out = null;
    try {
      out = new ObjectOutputStream(new FileOutputStream(saveFileName));
      out.writeObject(meetings);
      out.writeObject(contacts);
      out.flush();
    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      if (out != null) {
        try {
          out.close();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    }
  }
}
