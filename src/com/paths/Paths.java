package com.paths;

import java.util.Arrays;
import java.io.File;

class Paths {
    public static void main(String[] args) throws Exception{
        int fileOptionIndex = Arrays.asList(args).indexOf("-f");
        int cityOptionIndex = Arrays.asList(args).indexOf("-c");
        int lastIndex = args.length-1;
        String fileName = args[fileOptionIndex+1];
        String cityFileName = args[cityOptionIndex+1];
        String source = args[lastIndex-1];
        String destination = args[lastIndex];
        File pathFile = new File(fileName);
        File cityFile = new File(cityFileName);

        if(!pathFile.exists()){
            System.out.println("No database named "+fileName+" found");
            return;
        }
        if(!cityFile.exists()){
            System.out.println("No database named "+cityFile+" found");
            return;
        }

        PathReader pr;
        CitiesCountryManager cr;
        PathManager pathManager = null;
        try {
            pr = new PathReader(pathFile);
            cr = new CitiesCountryManager(cityFile);
            pathManager = new PathManager(pr, cr);
        } catch (DatabaseNotFoundException e) {
            System.out.println(e.getMessage());
        }

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