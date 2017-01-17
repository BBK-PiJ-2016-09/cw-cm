import java.util.Calendar;
import java.util.Set;
import java.util.*;

/**
 * A class to represent meetings
 *
 * Meetings have unique IDs, scheduled date and a list of participating contacts
 */

public class MeetingImpl implements Meeting {
    private int meetingId;
    private Calendar meetingDate;
    private Set<Contact> contactsSet;

    public static int totalMeetings = 0;


    public MeetingImpl(Calendar date, Set<Contact> contactsSet) {
        totalMeetings++;
        this.meetingId = totalMeetings;
        this.meetingDate = date;
        this.contactsSet = contactsSet;
    }

    public int getId() {
        return meetingId;
    }

    public Calendar getDate() {
        return meetingDate;
    }

    public Set<Contact> getContacts() {
        return this.contactsSet;

    }

}
