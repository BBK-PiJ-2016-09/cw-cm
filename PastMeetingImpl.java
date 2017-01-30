import java.util.Calendar;
import java.util.Set;
import java.util.*;

public class PastMeetingImpl extends MeetingImpl implements PastMeeting {

    private String meetingNotes = "";
    public PastMeetingImpl(Calendar date, Set<Contact> contactsSet, String notes){
        super(date, contactsSet);
        meetingNotes = notes;
    }
    public String getNotes() {
        return meetingNotes;
    }
}