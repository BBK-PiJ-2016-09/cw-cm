import java.util.Calendar;
import java.util.Set;
import java.util.*;

public class ContactManagerImpl {

    private ArrayList<FutureMeeting> futureMeetings = new ArrayList<FutureMeeting>();
    /**
     * Add a new meeting to be held in the future.
     *
     * An ID is returned when the meeting is put into the system. This
     * ID must be positive and non-zero.
     *
     * @param contacts a set of contacts that will participate in the meeting
     * @param date the date on which the meeting will take place
     * @return the ID for the meeting
     * @throws IllegalArgumentException if the meeting is set for a time
     *       in the past, of if any contact is unknown / non-existent.
     * @throws NullPointerException if the meeting or the date are null
     */
    public int addFutureMeeting(Calendar date, Set<Contact> contacts) {
        FutureMeeting futureMeetingToBeAdded = new FutureMeetingImpl(date, contacts);
        futureMeetings.add(futureMeetingToBeAdded);
        return futureMeetingToBeAdded.getId();
    }


}
