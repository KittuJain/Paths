package com.paths;

import java.util.Arrays;
import java.util.Map;

public class PathRunner {

    public void run(String[] input) throws Exception{
        int lastIndex = input.length-1;

        String source = input[lastIndex-1];
        String destination = input[lastIndex];

        int fileOptionIndex = Arrays.asList(input).indexOf("-f");
        int cityOptionIndex = Arrays.asList(input).indexOf("-c");
        String pathFile = input[fileOptionIndex + 1];
        String cityFile = input[cityOptionIndex + 1];

        PathsLib pl = new PathsLib(pathFile);
        CitiesCountryManager cm = new CitiesCountryManager(cityFile);
        Map<String, Map<String, Integer>> routes = pl.readCost(new FileScanner(pathFile).read());
        PathManager pathManager = new PathManager(pl, cm, routes);

        displayResult(source, destination, pathManager);

    }

    private void displayResult(String source, String destination, PathManager pathManager) throws Exception {
        String CITY_NOT_FOUND = "No city named 'CITY' in database";
        UserDisplay userDisplay = new UserDisplay();

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
