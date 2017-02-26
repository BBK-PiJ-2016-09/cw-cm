import java.util.Calendar;
import java.util.Set;
import java.util.*;
import java.io.Serializable;


public class PastMeetingImpl extends MeetingImpl implements PastMeeting, Serializable {

    private String meetingNotes = "";

    public PastMeetingImpl(Calendar date, Set<Contact> contactsSet, String notes) {
        super(date, contactsSet);
        meetingNotes = notes;
    }
    public String getNotes() {
        return meetingNotes;
    }

    public void addNotes(String note) {
        if (meetingNotes == ""){
            meetingNotes = note;
        } else {
            meetingNotes += "\n" + note;
        }
    }

}
