import com.paths.PathManager;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class PathManagerTest {

    @Test
    public void isCityPresent_checks_whether_city_is_present() {
        PathManager pathManager = new PathManager();
        assertFalse(pathManager.isCityPresent("Chennai"));
        assertTrue(pathManager.isCityPresent("Dubai"));
        assertTrue(pathManager.isCityPresent("Tokyo"));
        assertTrue(pathManager.isCityPresent("Singapore"));
        assertTrue(pathManager.isCityPresent("Beijing"));
    }

    @Test
    public void getPath_gets_possible_path_from_Bangalore_to_Singapore() {
        PathManager pathManager = new PathManager();
        List<List<String>> allPaths = pathManager.getPath("Bangalore", "Singapore");
        assertTrue(allPaths.get(0).get(0).equals("Bangalore"));
        assertTrue(allPaths.get(0).get(1).equals("Singapore"));
    }

    @Test
    public void getPath_gets_possible_path_from_Seoul_to_Beijing() {
        PathManager pathManager = new PathManager();
        List<List<String>> allPaths = pathManager.getPath("Seoul", "Beijing");
        assertTrue(allPaths.get(0).get(0).equals("Seoul"));
        assertTrue(allPaths.get(0).get(1).equals("Beijing"));
    }

    @Test
    public void getPath_gets_possible_path_from_Beijing_to_Tokyo() {
        PathManager pathManager = new PathManager();
        List<List<String>> allPaths = pathManager.getPath("Beijing", "Tokyo");
        assertTrue(allPaths.get(0).get(0).equals("Beijing"));
        assertTrue(allPaths.get(0).get(1).equals("Tokyo"));
    }

    @Test
    public void getPath_gets_possible_path_from_Singapore_to_Dubai() {
        PathManager pathManager = new PathManager();
        List<List<String>> allPaths = pathManager.getPath("Singapore", "Dubai");
        assertTrue(allPaths.get(0).get(0).equals("Singapore"));
//        assertTrue(allPaths.get(0).get(1).equals("Dubai"));
    }

    @Test
    public void getPath_gets_possible_path_from_Singapore_to_Seoul() {
        PathManager pathManager = new PathManager();
        List<List<String>> allPaths = pathManager.getPath("Singapore", "Seoul");
        assertTrue(allPaths.get(0).get(0).equals("Singapore"));
        assertTrue(allPaths.get(0).get(1).equals("Seoul"));
    }

    @Test
    public void getPath_gets_all_possible_paths_from_Bangalore_to_Tokyo() {
        PathManager pathManager = new PathManager();
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
        List<List<String>> allPaths = pathManager.getPath("Bangalore", "Tokyo");
        assertTrue(allPaths.get(0).equals(list1));
        assertTrue(allPaths.get(1).equals(list2));
    }

    @Test
    public void hasPath_returns_1_when_there_is_path_between_two_places(){
        PathManager pathManager = new PathManager();
        assertEquals(1, pathManager.hasPath("Bangalore", "Singapore"));
    }

    @Test
    public void hasPath_returns_1_when_there_is_path_between_two_indirect_places(){
        PathManager pathManager = new PathManager();
        assertEquals(1, pathManager.hasPath("Bangalore", "Tokyo"));
    }

    @Test
    public void hasDirectPath_returns_true_when_there_is_path_between_two_places(){
        PathManager pathManager = new PathManager();
        assertTrue(pathManager.hasDirectPath("Bangalore", "Singapore"));
    }

    @Test
    public void hasDirectPath_returns_false_when_there_is_no_direct_path_between_two_places(){
        PathManager pathManager = new PathManager();
        assertFalse(pathManager.hasDirectPath("Bangalore", "Tokyo"));
    }

    @Test
    public void hasIndirectPath_returns_1_when_there_is_path_between_two_places(){
        PathManager pathManager = new PathManager();
        assertEquals(1, pathManager.hasIndirectPath("Bangalore", "Tokyo", new ArrayList<String>()));
    }
}