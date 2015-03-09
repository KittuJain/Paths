package com.paths;

import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.io.File;

public class Path {
    static Map<String, List<String>> map = new HashMap<String, List<String>>();
    CitiesReader cr;
    List<String> subPath = new ArrayList<String>();

    static {
        List<String> bangalore = new ArrayList<String>();
        List<String> singapore = new ArrayList<String>();
        List<String> seoul = new ArrayList<String>();
        List<String> beijing = new ArrayList<String>();
        List<String> dubai = new ArrayList<String>();
        List<String> tokyo = new ArrayList<String>();
        bangalore.add("Singapore");
        singapore.add("Seoul");
        seoul.add("Singapore");
        dubai.add("Singapore");
        beijing.add("Seoul");
        tokyo.add("Beijing");
        singapore.add("Bangalore");
        singapore.add("Dubai");
        seoul.add("Beijing");
        beijing.add("Tokyo");
        map.put("Bangalore", bangalore);
        map.put("Singapore", singapore);
        map.put("Seoul", seoul);
        map.put("Beijing", beijing);
        map.put("Dubai", dubai);
        map.put("Tokyo", tokyo);
    }

    public Path() {
        map = map;
    }

    public Path(Map<String, List<String>> map, CitiesReader cr) {
        this.map = map;
        this.cr = cr;
    }

    public boolean isCityPresent(String city) {
        Set<String> sourceStations = map.keySet();
        if (sourceStations.contains(city))
            return true;
        else {
            for (String source : sourceStations) {
                List<String> destinations = map.get(source);
                if (destinations.contains(city))
                    return true;
            }
        }
        return false;
    }

    public List<String> getPath(String station1, String station2) {
        List<String> fullPath = new ArrayList<String>();
        fullPath.add(station1);
        fullPath = (givePath(fullPath, station1, station2));
        return fullPath;
    }

    private List<String> givePath(List<String> fullPath, String station1, String station2) {
        if (map.get(station1).contains(station2)) {
            fullPath.add(station2);
            return fullPath;
        }
        int size = map.get(station1).size();
        for (int i = 0; i < size; i++) {
            if (!fullPath.contains(map.get(station1).get(i))) {
                fullPath.add(map.get(station1).get(i));
                return givePath(fullPath, map.get(station1).get(i), station2);
            }
        }
        return fullPath;
    }

    public String printPath(String source, String destination) throws Exception {
        List<String> fullPath = getPath(source, destination);
        int size = fullPath.size();
        if((fullPath.get(0) == source) && (fullPath.get(size-1) == destination)){
            String fullRoute = "";
            for (int i = 0; i < size; i++) {
                String pathWithCity = fullPath.toArray()[i].toString();
                if (i > 0) {
                    fullRoute += "->" + pathWithCity + "[" + cr.getCountry(pathWithCity) + "]";
                } else {
                    fullRoute += "" + pathWithCity + "[" + cr.getCountry(pathWithCity) + "]";
                }
            }
            return fullRoute;
        }
        return "Path doesn't exist";
    }
}
