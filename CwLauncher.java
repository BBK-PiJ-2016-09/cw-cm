import java.util.Calendar;
import java.util.Set;
import java.util.*;
import java.io.*;
public class CwLauncher{
    public static void main(String[] args){
        Calendar currentDate;
        Calendar futureDate;
        Calendar pastDate;

        ContactManagerImpl testContactManager;
        Contact testContact;

        Set<Contact> testContactSet;
        int testContactId;
        int futureMeetingToBeAddedId;
        int pastMeetingToBeAddedId;


        currentDate = Calendar.getInstance();
        futureDate = Calendar.getInstance();
        futureDate.add(Calendar.DATE, 15);
        pastDate = Calendar.getInstance();
        pastDate.add(Calendar.DATE, -15);

        testContactManager = new ContactManagerImpl();
        testContactId = testContactManager.addNewContact("a contact name", "some contact notes");
        testContactSet = testContactManager.getContacts(testContactId);


        Iterator<Contact> iterator = testContactSet.iterator();
        testContact = iterator.next();

        futureMeetingToBeAddedId = testContactManager.addFutureMeeting(testContactSet, futureDate);
        pastMeetingToBeAddedId = testContactManager.addNewPastMeeting(testContactSet, pastDate, "");
        testContactManager.flush();
    }
}
