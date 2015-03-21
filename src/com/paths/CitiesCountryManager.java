package com.paths;

import java.io.*;
import java.util.*;

public class CitiesCountryManager {
    Map<String,String> countryRoutes;
    public CitiesCountryManager(File file)throws Exception{
        countryRoutes = readCity(file);
    }
    public Map<String,String> readCity(File fileName) throws Exception{
        Map<String,String> countryRoutes = new HashMap<String,String>();
            try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = ""+br.readLine();
            while(line != null){
                String places[] = line.split(",");
                countryRoutes.put(places[0],places[1]);
                line = br.readLine();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return countryRoutes;
    }

    public String getCountry(String city){
        return countryRoutes.get(city);
    }
}