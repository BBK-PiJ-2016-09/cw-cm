import java.util.Calendar;
import java.util.Set;
import java.util.*;

public class Mytests{
    public static void main(String[] args){

        Calendar myDate = Calendar.getInstance();
        Contact testContact = new ContactImpl("contactName");
        Set<Contact> testContactSet = new HashSet<>();
        PastMeeting testMeeting = new PastMeetingImpl(myDate, testContactSet, "Some notes about the meeting");
        System.out.println(testContactSet);
    }

}