import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

/**
 * A meeting that was held in the past.
 * It includes your notes about what happened and what was agreed.
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting,
        Serializable {

  /**
   * The notes taken in the meeting.
   */
  private String meetingNotes = "";

  /**
   * Constructor.
   *  @param date date of the meeting
   *  @param contactsSet the set of contacts that will go to the meeting
   *  @param notes notes for the meeting
   *  @author Enric Serra
   *  @version 1.0
   */
  public PastMeetingImpl(final Calendar date, final Set<Contact> contactsSet,
                         final String notes) {
    super(date, contactsSet);
    meetingNotes = notes;
  }

  /**
   * Returns the notes from the meeting.
   * If there are no notes, the empty string is returned.
   * @return the notes from the meeting.
   */
  public String getNotes() {
    return meetingNotes;
  }

  /**
   * Adds notes to a meeting.
   * If there are no notes, the notes are substituted by the new ones.
   * @param notes the notes to be added to the meeting
   */
  public void addNotes(final String notes) {
    if (meetingNotes == "") {
      meetingNotes = notes;
    } else {
      meetingNotes += "\n" + notes;
    }
  }

}
