import org.junit.*;
import static org.junit.Assert.*;

public class MeetingImplTests {
    Meeting testMeeting = new MeetingImpl();

    @Test
    public void getMeetingIdTest() {
        assertEquals(testMeeting.getId(), 1);
    }

}
