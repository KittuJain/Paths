import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.io.File;

class Paths{
	public static void main(String[] args) throws Exception {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		Map<String,String> countryRoutes = new HashMap<String,String>();
		String option = args[0];
		File fileName = new File(args[1]);
		String cityOption = args[2];
		File cityFile = new File(args[3]);
		String source = args[4];
		String destination = args[5];
		CitiesReader cr = new CitiesReader();
		Map<String,String> cityCountryRoutes = cr.readCity(cityFile);
		if(!fileName.exists()){
			System.out.println("No database named "+fileName+" found");
			return;
		}
		PathReader pr = new PathReader();
		map = pr.readPath(fileName);
		Path path = new Path(map,cityCountryRoutes);

		if(!path.isCityPresent(source)){
			System.out.println("No city named '"+source+"' in database");
			return;
		}
		if(!path.isCityPresent(destination)){
			System.out.println("No city named '"+destination+"' in database");
			return;
		}
		System.out.println(path.givePath(source,destination));
	}
}