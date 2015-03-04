import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.io.File;

class Path{
	public static void main(String[] args) throws IOException {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		String option = args[0];
		File fileName = new File(args[1]);
		String source = args[2];
		String destination = args[3];
		Paths paths = new Paths();
		PathReader pr = new PathReader();
		map = pr.readPath(fileName);
		
		if(!fileName.exists()){
			System.out.println("No database named "+fileName+" found");
		}
		if(!paths.isCityPresent(source)){
			System.out.println("No city named '"+source+"' in database");
			return;
		}
		if(!paths.isCityPresent(destination)){
			System.out.println("No city named '"+destination+"' in database");
			return;
		}
		System.out.println(paths.givePath(source,destination));
	}
}