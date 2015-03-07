import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class CitiesReader{
	Map<String,String> countryRoutes = new HashMap<String,String>();
	
	public void readCity() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("cities.txt"));
		String line = ""+br.readLine();
		while(line != null){
			String places[] = line.split(",");
			countryRoutes.put(places[0],places[1]);
			line = br.readLine();
		}
		Set<String> cities = countryRoutes.keySet();
	}

	public String getCountry(String city){
		return countryRoutes.get(city);
	}
}