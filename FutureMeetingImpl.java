import java.util.Calendar;
import java.util.Set;
import java.io.Serializable;



public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting, Serializable {
    public FutureMeetingImpl(Calendar date, Set<Contact> contactsSet) {
        super(date, contactsSet);
    }
}