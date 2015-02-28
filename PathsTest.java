import org.junit.Test;
import static org.junit.Assert.*;

public class PathsTest {
	
	@Test
	public void findFlight_returns_true_if_there_is_direct_flight_between_two_stations (){
		Paths paths = new Paths();
		assertEquals(paths.findFlight("Bangalore", "Singapore"),true);
	}
}