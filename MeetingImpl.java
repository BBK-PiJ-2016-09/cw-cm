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
    public static int totalMeetings = 0;

    /**
     * Returns the id of the meeting.
     *
     * @return the id of the meeting.
     */
    public MeetingImpl() {
        totalMeetings++;
        this.meetingId = totalMeetings;
    }

    public int getId() {
        return meetingId;
    }

    /**
     * Return the date of the meeting.
     *
     * @return the date of the meeting.
     */
    public Calendar getDate() {
        Calendar cal = Calendar.getInstance();
        return cal;
    }

    /**
     * Return the details of people that attended the meeting.
     *
     * The list contains a minimum of one contact (if there were
     * just two people: the user and the contact) and may contain an
     * arbitrary number of them.
     *
     * @return the details of people that attended the meeting.
     */
    public Set<Contact> getContacts() {
        Set<Integer> mySet = new HashSet<>();

        Set<Contact> contacts = new HashSet<>();
        return contacts;

    }
}
