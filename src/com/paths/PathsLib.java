package com.paths;

import java.util.*;

public class PathsLib {
    Map<String,Map<String,Integer>> routesMap = new HashMap<String,Map<String,Integer>>();

    public PathsLib(String fileName){
        this.routesMap = readCost(new FileScanner(fileName).read());
    }

    public Map<String, Map<String, Integer>> readCost(String routes){
        if(routes == null)
            System.exit(0);
        Map<String,Map<String,Integer>> routesMap = new HashMap<String,Map<String,Integer>>();
        for (String route : routes.split(System.lineSeparator())) {
            populateRoutesMap(route, routesMap, 0, 1);
            populateRoutesMap(route, routesMap, 1, 0);
        }
        return routesMap;
    }

    private void populateRoutesMap(String route, Map<String, Map<String, Integer>> routesMap, int sourceIndex, int destinationIndex) {
        String path[] = splitByComma(route);
        Map<String, Integer> destinations = routesMap.get(path[sourceIndex]);
        if (destinations == null) {
            destinations = new HashMap<String, Integer>();
            destinations.put(path[destinationIndex], Integer.parseInt(path[2]));
            routesMap.put(path[sourceIndex], destinations);
        }
        else
            destinations.put(path[destinationIndex], Integer.parseInt(path[2]));

    }

    private String[] splitByComma(String line) {
        String[] words = line.split(",");
        String[] updatedWords = new String[words.length];
        for (int count = 0; count < words.length; count++) {
            updatedWords[count] = words[count].trim();
        }
        return updatedWords;
    }

    private Integer getCost(String source, String destination) {
        return routesMap.get(source).get(destination);
    }

    public Integer getFullPathCost(List<String> path, String source, String destination) {
        int cost = 0;
        if (routesMap.get(source).keySet().contains(destination)) {
            return getCost(source, destination);
        } else {
            path.remove(0);
            for (String dest : path) {
                cost += getCost(source, dest);
                source = dest;
            }
            return cost;
        }
    }
}
