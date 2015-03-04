import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;

class Path{
	public static void main(String[] args) throws IOException {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		String source = args[0];
		String destination = args[1];
		Paths paths = new Paths();
		PathReader pr = new PathReader();
		map = pr.readPath();
		if(!paths.isCityPresent(source)){
			System.out.println("No city named '"+source+"' in database");
			return;
		}
		if(!paths.isCityPresent(destination)){
			System.out.println("No city named '"+destination+"' in database");
			return;
		}
		else
			System.out.println(paths.givePath(source,destination));
	}
}