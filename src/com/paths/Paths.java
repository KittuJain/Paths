package com.paths;

import java.util.Arrays;
import java.util.Map;
import java.io.File;

class Paths {
    public static void main(String[] args) throws Exception {
        Map<String, Map<String,Integer>> map;
        int fileOptionIndex = Arrays.asList(args).indexOf("-f");
        int cityOptionIndex = Arrays.asList(args).indexOf("-c");
        int lastIndex = args.length-1;
        File fileName = new File(args[fileOptionIndex+1]);
        File cityFile = new File(args[cityOptionIndex+1]);
        String source = args[lastIndex-1];
        String destination = args[lastIndex];

        if(!fileName.exists()){
            System.out.println("No database named "+fileName+" found");
            return;
        }
        if(!cityFile.exists()){
            System.out.println("No database named "+cityFile+" found");
            return;
        }

        PathReader pr = new PathReader(fileName);
        map = pr.readCost(fileName);
        CitiesCountryManager cr = new CitiesCountryManager(cityFile);
        PathManager pathManager = new PathManager(map, cr, pr);

        if(!pathManager.isCityPresent(source)){
            System.out.println("No city named '"+source+"' in database");
            return;
        }
        if(!pathManager.isCityPresent(destination)){
            System.out.println("No city named '" + destination + "' in database");
            return;
        }
        System.out.println(pathManager.printPath(source,destination));
    }
}