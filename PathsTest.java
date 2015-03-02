import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;

public class PathsTest {
	
	private Paths paths() {
		Paths path = new Paths();
		List<String> bangalore = new ArrayList<String>();
		List<String> singapore = new ArrayList<String>();
		List<String> seoul = new ArrayList<String>();
		List<String> beijing = new ArrayList<String>();
		bangalore.add("Singapore");
		singapore.add("Seoul");
		singapore.add("Dubai");
		seoul.add("Beijing");
		beijing.add("Tokyo");
		path.insertPaths("Bangalore", bangalore);
		path.insertPaths("Singapore", singapore);
		path.insertPaths("Seoul", seoul);
		path.insertPaths("Beijing", beijing);
		return path;
	}

	@Test
	public void isCityPresent_checks_whether_city_is_prsent (){
		Paths paths = paths();
		assertFalse(paths.isCityPresent("Chennai"));
		assertTrue(paths.isCityPresent("Dubai"));
		assertTrue(paths.isCityPresent("Tokyo"));
		assertTrue(paths.isCityPresent("Singapore"));
	}

	@Test
	public void findFlight_returns_true_if_there_is_direct_flight_between_Bangalore_and_Singapore (){
		Paths paths = paths();
		assertTrue(paths.findFlight("Bangalore", "Singapore"));
	}

	@Test
	public void findFlight_returns_false_if_there_is_no_flight_between_Bangalore_and_Tokyo (){
		Paths paths = paths();
		assertFalse(paths.findFlight("Bangalore", "Tokyo"));
	}

	@Test
	public void findFlight_returns_true_if_there_is_direct_flight_between_Seoul_and_Beijing (){
		Paths paths = paths();
		assertTrue(paths.findFlight("Seoul", "Beijing"));
	}

	@Test
	public void findFlight_returns_true_if_there_is_direct_flight_between_Beijing_and_Tokyo (){
		Paths paths = paths();
		assertTrue(paths.findFlight("Beijing", "Tokyo"));
	}
	
	@Test
	public void findFlight_returns_true_if_there_is_direct_flight_between_Singapore_and_Dubai (){
		Paths paths = paths();
		assertTrue(paths.findFlight("Singapore", "Dubai"));
	}

	@Test
	public void findFlight_returns_true_if_there_is_direct_flight_between_Singapore_and_Seoul (){
		Paths paths = paths();
		assertTrue(paths.findFlight("Singapore", "Seoul"));
	}
	
}