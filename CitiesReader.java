import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class CitiesReader{
	public Map<String,String> readCity(File fileName) throws Exception{
		Map<String,String> countryRoutes = new HashMap<String,String>();
		try{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line = ""+br.readLine();
			while(line != null){
					String places[] = line.split(",");
					countryRoutes.put(places[0],places[1]);
					line = br.readLine();
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return countryRoutes;
	}

	public String getCountry(Map<String,String> countryRoutes, String city){
		return countryRoutes.get(city);
	}
}