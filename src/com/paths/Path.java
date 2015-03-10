package com.paths;

import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Path {
    static Map<String, List<String>> map = new HashMap<String, List<String>>();
    CitiesReader cr;
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

//    public Path() {
//        map = map;
//    }

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

    public List<List<String>> getPath(String station1, String station2) {
        List<String> path = new ArrayList<String>();
        List<List<String>> allPaths = new ArrayList<List<String>>();
        givePath(path, allPaths,station1, station2);
        return allPaths;
    }

    private void givePath(List<String> path, List<List<String>> allPaths,String source, String destination) {
        path.add(source);
        if (source.equals(destination)) {
            allPaths.add(new ArrayList<String>(path));
            path.remove(source);
            return;
        }
        int size = map.get(source).size();
        List<String> destinations = map.get(source);
        for (int i = 0; i < destinations.size(); i++) {
            if (!path.contains(destinations.get(i))) {
                givePath(path, allPaths, destinations.get(i), destination);
            }
        }
        path.remove(source);
    }

    public String printPath(String source, String destination) throws Exception {
        List<List<String>> allPaths = getPath(source, destination);
        int size = allPaths.size();
        String fullRoute = "";
        for(int i = 0; i < size; i++){
            List<String> path = allPaths.get(i);
            int sizeOfEachPath = path.size();
            if((path.get(0).equals(source)) && (path.get(sizeOfEachPath-1).equals(destination))) {
                String route = "";
                for (int j = 0; j < sizeOfEachPath; j++) {
                    String pathWithCity = path.get(j);
                    if (j > 0) {
                        route += "->" + pathWithCity + "[" + cr.getCountry(pathWithCity) + "]";
                    } else {
                        route += (i+1)+". " + pathWithCity + "[" + cr.getCountry(pathWithCity) + "]";
                    }
                }
                fullRoute = fullRoute+"\n"+ route;
            }
        }
        return fullRoute;
    }
}