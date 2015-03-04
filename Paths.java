import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.io.File;

class Paths{
	public static void main(String[] args) throws IOException {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		String option = args[0];
		File fileName = new File(args[1]);
		String source = args[2];
		String destination = args[3];
		Path path = new Path();
		if(!fileName.exists()){
			System.out.println("No database named "+fileName+" found");
			return;
		}
		PathReader pr = new PathReader();
		map = pr.readPath(fileName);

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