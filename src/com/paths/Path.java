package com.paths;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Path {
    static Map<String, List<String>> routesMap = new HashMap<String, List<String>>();
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
        dubai.add("Seoul");
        routesMap.put("Bangalore", bangalore);
        routesMap.put("Singapore", singapore);
        routesMap.put("Seoul", seoul);
        routesMap.put("Beijing", beijing);
        routesMap.put("Dubai", dubai);
        routesMap.put("Tokyo", tokyo);
    }

    public Path() {
        routesMap = routesMap;
    }

    public Path(Map<String, List<String>> routesMap, CitiesReader cr) {
        this.routesMap = routesMap;
        this.cr = cr;
    }

    public boolean isCityPresent(String city) {
        Set<String> sourceStations = routesMap.keySet();
        if (sourceStations.contains(city))
            return true;
        else {
            for (String source : sourceStations) {
                List<String> destinations = routesMap.get(source);
                if (destinations.contains(city))
                    return true;
            }
        }
        return false;
    }
    public boolean hasDirectPath(String source, String destination){
        return (isCityPresent(source) && routesMap.get(source).indexOf(destination)> -1);
    }

    public int hasIndirectPath(String source, String destination, List<String> path) {
        path.add(source);
        if(source.equals(destination)) return 0;
        if(hasDirectPath(source, destination)) return 1;
        for (String city : routesMap.get(source)) {
            if(!path.contains(city) && hasIndirectPath(city, destination, path)==1)
                return 1;
        }
        return 0;
    }

    public int hasPath(String source, String destination){
        List<String> path = new ArrayList<String>();
        return (isCityPresent(source)) ? hasIndirectPath(source, destination, path) : 0;
    }

    public List<List<String>> getPath(String station1, String station2) {
        List<String> path = new ArrayList<String>();
        List<List<String>> allPaths = new ArrayList<List<String>>();
        givePath(path, allPaths, station1, station2);
        return allPaths;
    }

    private void givePath(List<String> path, List<List<String>> allPaths,String source, String destination) {
        path.add(source);
        if (source.equals(destination)) {
            allPaths.add(new ArrayList<String>(path));
            path.remove(source);
            return;
        }
        List<String> destinations = routesMap.get(source);
        for (String dest : destinations){
            if (!path.contains(dest)) {
                givePath(path, allPaths, dest, destination);
            }
        }
        path.remove(source);
    }

    public String printPath(String source, String destination) throws Exception {
        List<List<String>> allPaths = getPath(source, destination);
        String fullRoute = "";
        if(hasPath(source,destination) == 1)
            return getFullRoute(source, destination, allPaths, fullRoute);
        return "Path doesn't exist";
    }

    private String getFullRoute(String source, String destination, List<List<String>> allPaths, String fullRoute) {
        int size = allPaths.size();
        for (int i = 0; i < size; i++) {
            List<String> path = allPaths.get(i);
            int sizeOfEachPath = path.size();
            if ((path.get(0).equals(source)) && (path.get(sizeOfEachPath - 1).equals(destination))) {
                String route = "";
                route = getRouteString(i, path, sizeOfEachPath, route);
                try {
                    fullRoute = fullRoute + "\n" + route + new CostReader(new File("./Data/paths.txt")).getFullPathCost(path,source,destination);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return fullRoute;
    }

    private String getRouteString(int i, List<String> path, int sizeOfEachPath, String route) {
        for (int j = 0; j < sizeOfEachPath; j++) {
            String pathWithCity = path.get(j);
            if (j > 0)
                route += "->" + pathWithCity + "[" + cr.getCountry(pathWithCity) + "]";
            else
                route += (i + 1) + ". " + pathWithCity + "[" + cr.getCountry(pathWithCity) + "]";
        }
        return route;
    }
}
