import org.junit.Test;
import static org.junit.Assert.*;

public class PathsTest {
	private Paths paths() {
		Paths path = new Paths();
		path.insertPaths("Bangalore", "Singapore");
		path.insertPaths("Singapore", "Seoul");
		path.insertPaths("Singapore", "Dubai");
		path.insertPaths("Seoul", "Beijing");
		path.insertPaths("Beijing", "Tokyo");
		return path;
	}

	@Test
	public void findFlight_returns_true_if_there_is_direct_flight_between_Bangalore_and_Singapore (){
		Paths paths = paths();
		assertTrue(paths.findFlight("Bangalore", "Singapore"));
	}

	@Test
	public void findFlight_returns_true_if_there_is_no_flight_between_Bangalore_and_Tokyo (){
		Paths paths = paths();
		assertFalse(paths.findFlight("Bangalore", "Tokyo"));
	}
}