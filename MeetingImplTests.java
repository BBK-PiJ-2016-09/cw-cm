import org.junit.*;
import static org.junit.Assert.*;
import java.util.Calendar;

public class MeetingImplTests {
    Meeting testMeeting = new MeetingImpl();

    @Test
    public void getMeetingIdTest() {
        assertEquals(testMeeting.getId(), 2);
    }

    @Test
    public void getDateTest() {
        Calendar testCalendar = Calendar.getInstance();
        assertEquals(testMeeting.getDate().getClass(), testCalendar.getClass());
    }
}
