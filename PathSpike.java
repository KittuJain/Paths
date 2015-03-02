import java.util.Map;
import java.util.HashMap;

// class City{
// 	private String cityName;
// 	City(String cityName){
// 		this.cityName = cityName;
// 	}
// 	public String toString(){
// 		return cityName;
// 	}
// }

class PathSpike {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Bangalore", "Singapore");
		map.put("Singapore", "Seoul");
		map.put("Singapore", "Dubai");
		map.put("Seoul", "Beijing");
		map.put("Beijing", "Tokyo");
		System.out.println(map.get("Singapore").equals("Dubai"));
	}
}