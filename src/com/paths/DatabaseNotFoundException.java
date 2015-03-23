package com.paths;

public class DatabaseNotFoundException extends Exception{
    private String message;

    DatabaseNotFoundException(String fileName) {
        this.message = "No database named \"" + fileName + "\" found";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
