import java.util.Calendar;
import java.util.Set;
import java.util.*;

public class PastMeetingImpl extends MeetingImpl implements PastMeeting {


    public PastMeetingImpl(Calendar date, Set<Contact> contactsSet){
        super(date, contactsSet);
    }
    public String getNotes() {
        return "";
    }
}