import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class PathReader{
	public Map<String,List<String>> readPath(File fileName) throws IOException{
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = ""+br.readLine();
		while(line!=null){
			String path[] = line.split(",");
			List<String> destinations = map.get(path[0]);
			if(destinations == null){
				destinations = new ArrayList<String>();
				destinations.add(path[1]);				
				map.put(path[0],destinations);
			}
			else{
				destinations.add(path[1]);
			}
			line = br.readLine();
		}
		return map;
	}
}