package com.paths;

import java.util.Arrays;
import java.util.Map;

class Paths {
    public static void main(String[] args) throws Exception{
        int lastIndex = args.length-1;

        String source = args[lastIndex-1];
        String destination = args[lastIndex];

        int fileOptionIndex = Arrays.asList(args).indexOf("-f");
        int cityOptionIndex = Arrays.asList(args).indexOf("-c");

        String pathFile = args[fileOptionIndex + 1];
        String cityFile = args[cityOptionIndex + 1];

        PathsLib pl = new PathsLib(pathFile);
        CitiesCountryManager cm = new CitiesCountryManager(cityFile);
        Map<String, Map<String, Integer>> routes = pl.readCost(new FileScanner(pathFile).read());
        PathManager pathManager = new PathManager(pl, cm, routes);

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