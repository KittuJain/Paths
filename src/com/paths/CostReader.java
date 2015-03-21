package com.paths;

import java.util.*;
import java.io.*;

public class CostReader {
    Map<String,Map<String,Integer>> routesMap = new HashMap<String,Map<String,Integer>>();
    public CostReader(File file)throws Exception{
        routesMap = readCost(file);
    }
    Map<String,Map<String,Integer>> readCost(File fileName) throws Exception{
        Map<String,Map<String,Integer>> routesMap = new HashMap<String,Map<String,Integer>>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = "" + br.readLine();
            while (line != null) {
                String path[] = line.split(",");
                Map<String, Integer> destinations = routesMap.get(path[0]);
                if (destinations == null) {
                    destinations = new HashMap<String, Integer>();
                    destinations.put(path[1], Integer.parseInt(path[2]));
                    routesMap.put(path[0], destinations);
                } else {
                    destinations.put(path[1], Integer.parseInt(path[2]));
                }
                destinations = routesMap.get(path[1]);
                if (destinations == null) {
                    destinations = new HashMap<String, Integer>();
                    destinations.put(path[0], Integer.parseInt(path[2]));
                    routesMap.put(path[1], destinations);
                } else {
                    destinations.put(path[0], Integer.parseInt(path[2]));
                }
                line = br.readLine();
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return routesMap;
    }
    private Integer getCost(String source,String destination){
        return routesMap.get(source).get(destination);
    }

    public Integer getFullPathCost(List<String> path,String source,String destination){
        int cost = 0;
        if(routesMap.get(source).keySet().contains(destination)){
            return getCost(source,destination);
        }else {
            path.remove(0);
            for (String dest : path){
                cost += getCost(source,dest);
                source = dest;
            }
            return cost;
        }
    }
}
