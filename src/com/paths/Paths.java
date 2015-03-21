package com.paths;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.io.File;

class Paths {
    public static void main(String[] args) throws Exception {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
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
        PathReader pr = new PathReader();
        map = pr.readPath(fileName);
        CostReader costR = new CostReader(fileName);
        CitiesReader cr = new CitiesReader(cityFile);
        Path path = new Path(map,cr,costR);

        if(!path.isCityPresent(source)){
            System.out.println("No city named '"+source+"' in database");
            return;
        }
        if(!path.isCityPresent(destination)){
            System.out.println("No city named '" + destination + "' in database");
            return;
        }
        System.out.println(path.printPath(source,destination));
    }
}