import java.util.Calendar;
import java.util.Set;

public class FutureMeetingImpl extends MeetingImpl {
    public FutureMeetingImpl(Calendar date, Set<Contact> contactsSet) {
        super(date, contactsSet);
    }
}