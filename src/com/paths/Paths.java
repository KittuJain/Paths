package com.paths;

import java.util.Arrays;
import java.util.Map;

class Paths {
    public static void main(String[] args) throws Exception{
        int fileOptionIndex = Arrays.asList(args).indexOf("-f");
        int cityOptionIndex = Arrays.asList(args).indexOf("-c");
        int lastIndex = args.length-1;
        String pathFileName = args[fileOptionIndex+1];
        String cityFileName = args[cityOptionIndex+1];
        String source = args[lastIndex-1];
        String destination = args[lastIndex];
        PathsLib pathsLib = new PathsLib(pathFileName);
        CitiesCountryManager citiesCountryManager = new CitiesCountryManager(cityFileName);
        Map<String, Map<String, Integer>> routes = pathsLib.readCost(new FileScanner(pathFileName).read());
        PathManager pathManager = new PathManager(pathsLib, citiesCountryManager, routes);

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