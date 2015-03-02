import java.util.*;

public class Paths {
	static Map<String, String> map = new HashMap<String, String>();
	public void insertPaths (String source, String destination){
		map.put(source, destination);
	}

	public boolean findFlight(String station1, String station2){
		return map.get(station1).equals(station2);
	}
}