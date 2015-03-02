import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.HashMap;

public class Paths {
	static Map<String, List<String>> map = new HashMap<String, List<String>>();
	
	public void insertPaths (String source, List destination){
		map.put(source, destination);
	}

	public static boolean isCityPresent(String city){
		Set<String> sourceStations = map.keySet(); //[Beijing, Singapore, Seoul, Bangalore]

		if(sourceStations.contains(city))
			return true;
		else{
			for(String source : sourceStations){
				List<String> destinations = map.get(source ) ;
				if(destinations.contains(city))
					return true;
			}
		}
		return false;
	}

	public boolean findFlight(String station1, String station2){
		return map.get(station1).contains(station2);
	}
}