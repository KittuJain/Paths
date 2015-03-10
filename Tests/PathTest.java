import com.paths.Path;
import com.paths.PathReader;
import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class PathTest {

    @Test
    public void isCityPresent_checks_whether_city_is_prsent (){
        PathReader pr = new PathReader();
        Path path = new Path();
        assertFalse(path.isCityPresent("Chennai"));
        assertTrue(path.isCityPresent("Dubai"));
        assertTrue(path.isCityPresent("Tokyo"));
        assertTrue(path.isCityPresent("Singapore"));
        assertTrue(path.isCityPresent("Beijing"));
    }

    @Test
    public void getPath_gets_possible_path_from_Bangalore_to_Singapore (){
        Path path = new Path();
        List<List<String>> allPaths = path.getPath("Bangalore", "Singapore");
        assertTrue(allPaths.get(0).get(0).equals("Bangalore"));
        assertTrue(allPaths.get(0).get(1).equals("Singapore"));
    }

    @Test
    public void getPath_gets_possible_path_from_Seoul_to_Beijing (){
        Path path = new Path();
        List<List<String>> allPaths = path.getPath("Seoul", "Beijing");
        assertTrue(allPaths.get(0).get(0).equals("Seoul"));
        assertTrue(allPaths.get(0).get(1).equals("Beijing"));
    }

    @Test
    public void getPath_gets_possible_path_from_Beijing_to_Tokyo (){
        Path path = new Path();
        List<List<String>> allPaths = path.getPath("Beijing","Tokyo");
        assertTrue(allPaths.get(0).get(0).equals("Beijing"));
        assertTrue(allPaths.get(0).get(1).equals("Tokyo"));
    }

    @Test
    public void getPath_gets_possible_path_from_Singapore_to_Dubai (){
        Path path = new Path();
        List<List<String>> allPaths = path.getPath("Singapore","Dubai");
        assertTrue(allPaths.get(0).get(0).equals("Singapore"));
        assertTrue(allPaths.get(0).get(1).equals("Dubai"));
    }

    @Test
    public void getPath_gets_possible_path_from_Singapore_to_Seoul (){
        Path path = new Path();
        List<List<String>> allPaths = path.getPath("Singapore","Seoul");
        assertTrue(allPaths.get(0).get(0).equals("Singapore"));
        assertTrue(allPaths.get(0).get(1).equals("Seoul"));
    }

    @Test
    public void getPath_gets_all_possible_paths_from_Bangalore_to_Tokyo (){
        Path path = new Path();
        List<String> list1 = new ArrayList<String>();
        list1.add("Bangalore");
        list1.add("Singapore");
        list1.add("Seoul");
        list1.add("Beijing");
        list1.add("Tokyo");
        List<String> list2 = new ArrayList<String>();
        list2.add("Bangalore");
        list2.add("Singapore");
        list2.add("Dubai");
        list2.add("Seoul");
        list2.add("Beijing");
        list2.add("Tokyo");
        List<List<String>> allPaths = path.getPath("Bangalore","Tokyo");
        assertTrue(allPaths.get(0).equals(list1));
        assertTrue(allPaths.get(1).equals(list2));
    }
}