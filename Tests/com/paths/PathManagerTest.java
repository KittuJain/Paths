package com.paths;

import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;

public class PathManagerTest {
    private PathManager pathManager;

    @Before
    public void setUp() throws Exception {
        PathsLib pl = new PathsLib("./Data/paths.txt");
        CitiesCountryManager cr = new CitiesCountryManager("./Data/cities.txt");
        pathManager = new PathManager(pl, cr, pl.readCost(new FileScanner("./Data/paths.txt").read()));
    }

    @Test
    public void isCityPresent_checks_whether_city_is_present() throws Exception {
        assertFalse(pathManager.isCityPresent("Chennai"));
        assertTrue(pathManager.isCityPresent("Dubai"));
        assertTrue(pathManager.isCityPresent("Tokyo"));
        assertTrue(pathManager.isCityPresent("Singapore"));
        assertTrue(pathManager.isCityPresent("Beijing"));
    }

    @Test
    public void getPath_gets_possible_path_from_Bangalore_to_Singapore() throws Exception {
        List<List<String>> allPaths = pathManager.getPath("Bangalore", "Singapore");
        assertEquals("Bangalore", allPaths.get(0).get(0));
        assertEquals("Singapore", allPaths.get(0).get(1));
    }

    @Test
    public void getPath_gets_possible_path_from_Seoul_to_Beijing() throws Exception {
        List<List<String>> allPaths = pathManager.getPath("Seoul", "Beijing");
        assertEquals("Seoul", allPaths.get(0).get(0));
        assertEquals("Beijing", allPaths.get(0).get(1));
    }

    @Test
    public void getPath_gets_possible_path_from_Beijing_to_Tokyo() throws Exception {
        List<List<String>> allPaths = pathManager.getPath("Beijing", "Tokyo");
        assertEquals("Beijing", allPaths.get(0).get(0));
        assertEquals("Tokyo", allPaths.get(0).get(1));
    }

    @Test
    public void getPath_gets_possible_path_from_Singapore_to_Dubai() throws Exception {
        List<List<String>> allPaths = pathManager.getPath("Singapore", "Dubai");
        List<String> list1 = new ArrayList<String>();
        list1.add("Singapore");
        list1.add("Seoul");
        list1.add("Dubai");
        List<String> list2 = new ArrayList<String>();
        list2.add("Singapore");
        list2.add("Dubai");
        assertEquals(list1, allPaths.get(0));
        assertEquals(list2, allPaths.get(1));
    }

    @Test
    public void getPath_gets_possible_path_from_Singapore_to_Seoul() throws Exception {
        List<List<String>> allPaths = pathManager.getPath("Singapore", "Seoul");
        assertEquals("Singapore", allPaths.get(0).get(0));
        assertEquals("Seoul", allPaths.get(0).get(1));
    }

    @Test
    public void getPath_gets_all_possible_paths_from_Bangalore_to_Tokyo() throws Exception {
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
        assertEquals(list1, allPaths.get(0));
        assertEquals(list2, allPaths.get(1));
    }

    @Test
    public void hasPath_returns_1_when_there_is_path_between_two_places() throws Exception {
        assertEquals(1, pathManager.hasPath("Bangalore", "Singapore"));
    }

    @Test
    public void hasPath_returns_1_when_there_is_path_between_two_indirect_places() throws Exception {
        assertEquals(1, pathManager.hasPath("Bangalore", "Tokyo"));
    }

    @Test
    public void hasDirectPath_returns_true_when_there_is_path_between_two_places() throws Exception {
        assertTrue(pathManager.hasDirectPath("Bangalore", "Singapore"));
    }

    @Test
    public void hasDirectPath_returns_false_when_there_is_no_direct_path_between_two_places() throws Exception {
        assertFalse(pathManager.hasDirectPath("Bangalore", "Tokyo"));
    }

    @Test
    public void hasIndirectPath_returns_1_when_there_is_path_between_two_places() throws Exception {
        assertEquals(1, pathManager.hasIndirectPath("Bangalore", "Tokyo", new ArrayList<String>()));
    }
}