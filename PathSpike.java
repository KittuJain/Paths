import java.util.Map;
import java.util.HashMap;

class Source{
	private String name;
	Source(String name){
		this.name = name;
	}
	public String toString(){
		return name;
	}
	public boolean equals(Object other) {
		return name.equals(((Source) other).name);
	}

	public int hashCode() {
		return this.name.hashCode();
	}
}

class Destination{
	private String name;
	Destination(String name){
		this.name = name;
	}
	public String toString(){
		return name;
	}
	public boolean equals(Object other) {
		return name.equals(((Destination) other).name);
	}

	public int hashCode() {
		return this.name.hashCode();
	}
}

class PathSpike {
	public static void main(String[] args) {
		Map<Source, Destination> map = new HashMap<Source, Destination>();
		map.put(new Source("Bangalore"), new Destination("Singapore"));
		map.put(new Source("Singapore"), new Destination("Seoul"));
		map.put(new Source("Singapore"), new Destination("Dubai"));
		map.put(new Source("Seoul"), new Destination("Beijing"));
		map.put(new Source("Beijing"), new Destination("Tokyo"));
		System.out.println(map.get(new Source("Singapore")));
		System.out.println(map.get(new Source("Singapore")).equals(new Destination("Dubai")));
	}
}