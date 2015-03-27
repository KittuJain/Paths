package com.paths;

import java.util.Arrays;
import java.util.Map;

public class PathRunner {

    public void run(String[] args) throws Exception{
        String CITY_NOT_FOUND = "No city named 'CITY' in database";
        UserDisplay userDisplay = new UserDisplay();
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
            userDisplay.show(CITY_NOT_FOUND.replace("CITY",source));
            return;
        }

        if(!pathManager.isCityPresent(destination)){
            userDisplay.show(CITY_NOT_FOUND.replace("CITY",destination));
            return;
        }
        userDisplay.show(pathManager.printPath(source, destination));

    }
}
