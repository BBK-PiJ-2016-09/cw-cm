package impl;

import java.util.Calendar;
import java.util.Set;
import specs.Contact;
import specs.Meeting;


/**
 * A class to represent meetings.
 * <p></p>
 * Meetings have unique IDs, scheduled date and a list of participating contacts
 */

public class MeetingImpl implements Meeting {
  /**
   * The id of the meeting.
   */
  private int meetingId;

  /**
   * The date of the meeting.
   */
  private Calendar meetingDate;

  /**
   * The Contacts in the meeting.
   */
  private Set<Contact> contactsSet;

  /**
   * The total meetings ever arranged.
   */
  public static int totalMeetings = 0;

  /**
   * Constructor.
   *  @param date date of the meeting
   *  @param contactsSetForThisMeeting the set of contacts that will
   *                                   go to the meeting
   *  @author Enric Serra
   *  @version 1.0
   */
  public MeetingImpl(final Calendar date,
                     final Set<Contact> contactsSetForThisMeeting) {
    totalMeetings++;
    this.meetingId = totalMeetings;
    this.meetingDate = date;
    this.contactsSet = contactsSetForThisMeeting;
  }

  /**
   * Returns the id of the meeting.
   * <p></p>
   * @return the id of the meeting.
   */
  public int getId() {
    return meetingId;
  }

  /**
   * Returns the date of the meeting.
   * <p></p>
   * @return the date of the meeting.
   */
  public Calendar getDate() {
    return meetingDate;
  }

  /**
   * Returns the contacts from the meeting.
   * <p></p>
   * @return the contacts of the meeting.
   */
  public Set<Contact> getContacts() {
    return this.contactsSet;
  }

}
