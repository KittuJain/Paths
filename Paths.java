import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Paths {
	public int flag = 0;
	static Map<String, List<String>> map = new HashMap<String, List<String>>();
	
	static{
		List<String> bangalore = new ArrayList<String>();
		List<String> singapore = new ArrayList<String>();
		List<String> seoul = new ArrayList<String>();
		List<String> beijing = new ArrayList<String>();
		List<String> dubai = new ArrayList<String>();
		List<String> tokyo = new ArrayList<String>();
		bangalore.add("Singapore");
		singapore.add("Bangalore");
		singapore.add("Seoul");
		seoul.add("Singapore");
		singapore.add("Dubai");
		dubai.add("Singapore");
		seoul.add("Beijing");
		beijing.add("Seoul");
		beijing.add("Tokyo");
		tokyo.add("Beijing");
		map.put("Bangalore", bangalore);
		map.put("Singapore", singapore);
		map.put("Seoul", seoul);
		map.put("Beijing", beijing);
		map.put("Dubai", dubai);
		map.put("Tokyo", tokyo);
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

	public boolean hasPath(String station1, String station2){
		List<String> fullPath = new ArrayList<String>();
		fullPath.add(station1);
		return (getFullPath(station1,station2,fullPath)==1) ? true : false;
	}

	public int getFullPath(String station1, String station2, List<String> path){
		int index = 0;
		if(map.get(station1) == null) return flag;
		if(map.get(station1).contains(station2)) flag = 1;
		else{
			if(!path.contains((map.get(station1)).get(index))){
				path.add((map.get(station1)).get(index));
				getFullPath((map.get(station1)).get(index),station2,path);
			}
			else{
				path.add((map.get(station1)).get(index++));
				getFullPath((map.get(station1)).get(index++),station2,path);
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		String source = args[0];
		String destination = args[1];
		Paths paths = new Paths();
		if(!paths.isCityPresent(source)){
			System.out.println("No city named '"+source+"' in database");
			return;
		}
		if(!paths.isCityPresent(destination)){
			System.out.println("No city named '"+destination+"' in database");
			return;
		}
		else
			System.out.println(paths.hasPath(source,destination));
	}
}