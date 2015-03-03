import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

public class Paths {
	static Map<String, List<String>> map = new HashMap<String, List<String>>();
	Queue path = new LinkedList();
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
		Set<String> sourceStations = map.keySet(); //[Beijing, Singapore, Seoul, Tokyo, Dubai, Bangalore]
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
		path.add(station1);
		return (getFullPath(station1,station2) == 1) ? true : false;
	}

	public int getFullPath(String station1, String station2){
		if(map.get(station1) == null) return 0;
		if(map.get(station1).contains(station2)){
			path.add(station2);
			return 1;
		}
		if(!map.get(station1).contains(station2)){			
			int size = map.get(station1).size();
			for(int i = 0; i < size; i++){
				if(!path.contains(map.get(station1).get(i))){
					path.add(map.get(station1).get(i));
					return getFullPath(map.get(station1).get(i),station2);
				}
			}
		}
		return 0;
	}


	public String givePath(String source, String destination){
		boolean hasPath = hasPath(source,destination);
		int size = path.size();
		String fullPath = "";
		for(int i = 0; i < size; i++){
			if(i>0)
				fullPath += "->"+path.poll();
			else
				fullPath += ""+path.poll();
		}
		return fullPath;
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