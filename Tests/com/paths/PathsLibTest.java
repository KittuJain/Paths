package com.paths;

import org.junit.Before;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

public class PathsLibTest {
    PathsLib pathsLib;

    @Before
    public void setUp() throws Exception {
        pathsLib = new PathsLib("./Data/paths.txt");
    }

    @Test
    public void test_getFullPathCost_gets_the_cost_of_full_path() {
        List<String> path = new ArrayList<String>();
        path.add("Bangalore");
        path.add("Singapore");
        path.add("Seoul");
        Integer cost = pathsLib.getFullPathCost(path, "Bangalore", "Seoul");
        assertEquals(27000,cost,0.001);
    }
}