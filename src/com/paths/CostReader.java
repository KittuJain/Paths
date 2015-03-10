package com.paths;

import java.util.*;
import java.io.*;

public class CostReader {
    static Map<String,Map<String,Integer>> readCost(String fileName) throws Exception{
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
    public Integer getCost(Map<String,Map<String,Integer>> routesMap,String source,String destination){
        return routesMap.get(source).get(destination);
    }
//    public Integer getFullPathCost(){
//        Integer cost = 0;
//        return cost;
//    }

    public static void main(String args[]) throws Exception {
        Map<String,Map<String,Integer>> routesMap = new HashMap<String,Map<String,Integer>>();
        CostReader cr = new CostReader();
        routesMap = readCost("./Data/paths.txt");
        System.out.println(cr.getCost(routesMap, "Bangalore", "Singapore"));
//        System.out.println(cr.getFullPathCost());
    }
}
