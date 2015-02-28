import java.util.Map;
import java.util.HashMap;

class City{
	private String cityName;
	City(String cityName){
		this.cityName = cityName;
	}
	public String toString(){
		return cityName;
	}
}

class PathSpike {
	public static void main(String[] args) {
		// String station1 = args[0];
		// String station2 = args[1];
		Map<City, City> map = new HashMap<City, City>();
		map.put(new City("Bangalore"), new City("Singapore"));
		map.put(new City("Singapore"), new City("Seoul"));
		map.put(new City("Singapore"), new City("Dubai"));
		map.put(new City("Seoul"), new City("Beijing"));
		map.put(new City("Beijing"), new City("Tokyo"));

		for(City source: map.keySet()) {
			if(source == new City("Bangalore") && map.get(source) == new City("Singapore")){
				System.out.println(true);
				break;
			}
			else{
				System.out.println(false);
				break;
			}
			// System.out.println(source+", " + map.get(source));
		}
		
	}
}