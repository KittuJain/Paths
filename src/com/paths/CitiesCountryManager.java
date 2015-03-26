package com.paths;

import java.util.*;

public class CitiesCountryManager {
    Map<String,String> countryRoutes;

    public CitiesCountryManager(String fileName){
        this.countryRoutes = readCity(new FileScanner(fileName).read());
    }

    public Map<String,String> readCity(String cities){
        if(cities == null)
            System.exit(0);
        Map<String,String> countryRoutes = new HashMap<String,String>();
        for (String city : cities.split(System.lineSeparator())) {
            String places[] = splitByComma(city);
            countryRoutes.put(places[0],places[1]);
        }
        return countryRoutes;
    }

    public String getCountry(String city){
        return countryRoutes.get(city);
    }

    private String[] splitByComma(String line) {
        String[] words = line.split(",");
        String[] updatedWords = new String[words.length];
        for (int count = 0; count < words.length; count++) {
            updatedWords[count] = words[count].trim();
        }
        return updatedWords;
    }
}