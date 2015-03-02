import org.junit.Test;
import static org.junit.Assert.*;


public class PathsTest {
	
	@Test
	public void isCityPresent_checks_whether_city_is_prsent (){
		Paths paths = new Paths();
		assertFalse(paths.isCityPresent("Chennai"));
		assertTrue(paths.isCityPresent("Dubai"));
		assertTrue(paths.isCityPresent("Tokyo"));
		assertTrue(paths.isCityPresent("Singapore"));
	}

	@Test
	public void findFlight_returns_true_if_there_is_direct_flight_between_Bangalore_and_Singapore (){
		Paths paths = new Paths();
		assertTrue(paths.findFlight("Bangalore", "Singapore"));
	}

	@Test
	public void findFlight_returns_false_if_there_is_no_flight_between_Bangalore_and_Tokyo (){
		Paths paths = new Paths();
		assertFalse(paths.findFlight("Bangalore", "Tokyo"));
	}

	@Test
	public void findFlight_returns_true_if_there_is_direct_flight_between_Seoul_and_Beijing (){
		Paths paths = new Paths();
		assertTrue(paths.findFlight("Seoul", "Beijing"));
	}

	@Test
	public void findFlight_returns_true_if_there_is_direct_flight_between_Beijing_and_Tokyo (){
		Paths paths = new Paths();
		assertTrue(paths.findFlight("Beijing", "Tokyo"));
	}
	
	@Test
	public void findFlight_returns_true_if_there_is_direct_flight_between_Singapore_and_Dubai (){
		Paths paths = new Paths();
		assertTrue(paths.findFlight("Singapore", "Dubai"));
	}

	@Test
	public void findFlight_returns_true_if_there_is_direct_flight_between_Singapore_and_Seoul (){
		Paths paths = new Paths();
		assertTrue(paths.findFlight("Singapore", "Seoul"));
	}
	
}