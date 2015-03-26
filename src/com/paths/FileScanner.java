package com.paths;

import java.io.IOException;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

public class FileScanner {
    private String path;

    public FileScanner(String path) {
        this.path = path;
    }

    public String read() {
        try {
            return new String(readAllBytes(get(path)));
        } catch (IOException e) {
            System.out.println("No database named "+path+" found");
        }
        return null;
    }
}
