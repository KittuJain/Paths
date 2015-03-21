package com.paths;

import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class PathManager {
    static Map<String, Map<String, Integer>> routesMap = new HashMap<String, Map<String, Integer>>();
    CitiesCountryManager cr;
    private PathReader pr;

    static {
        Map<String, Integer> bangalore = new HashMap<String, Integer>();
        Map<String, Integer> singapore = new HashMap<String, Integer>();
        Map<String, Integer> seoul = new HashMap<String, Integer>();
        Map<String, Integer> beijing = new HashMap<String, Integer>();
        Map<String, Integer> dubai = new HashMap<String, Integer>();
        Map<String, Integer> tokyo = new HashMap<String, Integer>();
        bangalore.put("Singapore",7000);
        singapore.put("Seoul", 20000);
        seoul.put("Singapore", 20000);
        dubai.put("Singapore", 9000);
        beijing.put("Seoul", 6000);
        tokyo.put("Beijing", 4000);
        singapore.put("Bangalore", 7000);
        singapore.put("Dubai", 16000);
        seoul.put("Beijing", 6000);
        seoul.put("Dubai", 9000);
        beijing.put("Tokyo", 4000);
        dubai.put("Seoul", 9000);
        routesMap.put("Bangalore", bangalore);
        routesMap.put("Singapore", singapore);
        routesMap.put("Seoul", seoul);
        routesMap.put("Beijing", beijing);
        routesMap.put("Dubai", dubai);
        routesMap.put("Tokyo", tokyo);
    }

    public PathManager() {}

    public PathManager(Map<String, Map<String, Integer>> routesMap, CitiesCountryManager cr, PathReader pr) {
        this.routesMap = routesMap;
        this.cr = cr;
        this.pr = pr;
    }

    public boolean isCityPresent(String city) {
        Set<String> sourceStations = routesMap.keySet();
        return (sourceStations.contains(city)) ? true : false;
    }
    public boolean hasDirectPath(String source, String destination){
        Map<String, Integer> destinationsWithCost = routesMap.get(source);
        List<String> destinations = new ArrayList<String>(destinationsWithCost.keySet());
        return (isCityPresent(source) && destinations.indexOf(destination)> -1);
    }

    public int hasIndirectPath(String source, String destination, List<String> path) {
        path.add(source);
        if(source.equals(destination)) return 0;
        if(hasDirectPath(source, destination)) return 1;
        Map<String, Integer> destinationsWithCost = routesMap.get(source);
        List<String> destinations = new ArrayList<String>(destinationsWithCost.keySet());
        for (String city : destinations) {
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
        Map<String, Integer> destinationsWithCost = routesMap.get(source);
        List<String> destinations = new ArrayList<String>(destinationsWithCost.keySet());
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
                    fullRoute += "\n" + route + "\nTotal Cost: " +
                            pr.getFullPathCost(path, source, destination);
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
