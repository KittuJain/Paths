import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Paths {
	static Map<String, List<String>> map = new HashMap<String, List<String>>();
	
	static{
		List<String> bangalore = new ArrayList<String>();
		List<String> singapore = new ArrayList<String>();
		List<String> seoul = new ArrayList<String>();
		List<String> beijing = new ArrayList<String>();
		bangalore.add("Singapore");
		singapore.add("Seoul");
		singapore.add("Dubai");
		seoul.add("Beijing");
		beijing.add("Tokyo");
		map.put("Bangalore", bangalore);
		map.put("Singapore", singapore);
		map.put("Seoul", seoul);
		map.put("Beijing", beijing);
	}

	public boolean isCityPresent(String city){
		Set<String> sourceStations = map.keySet(); //[Beijing, Singapore, Seoul, Bangalore]

		if(sourceStations.contains(city))
			return true;
		else{
			for(String source : sourceStations){
				List<String> destinations = map.get(source) ;
				if(destinations.contains(city))
					return true;
			}
		}
		return false;
	}

	public boolean findFlight(String station1, String station2){
		return map.get(station1).contains(station2);
	}
	public static void main(String[] args) {
		String source = args[0];
		String destination = args[1];
		Paths paths = new Paths();
		if(!paths.isCityPresent(source)){
			System.out.println("City "+source+" doesn't have any flight");
			return;
		}
		if(!paths.isCityPresent(destination)){
			System.out.println("City "+destination+" doesn't have any flight");
			return;
		}else
		System.out.println(paths.findFlight(source,destination));
	}
}