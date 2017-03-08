import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;


/**
 * A meeting to be held in the future.
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting,
        Serializable {

  /**
    * Constructor.
    *  @param date date of the meeting
    *  @param contactsSet the set of contacts that will go to the meeting
    *  @author Enric Serra
    *  @version 1.0
    */
  public FutureMeetingImpl(final Calendar date,
                           final Set<Contact> contactsSet) {
    super(date, contactsSet);
  }
}
