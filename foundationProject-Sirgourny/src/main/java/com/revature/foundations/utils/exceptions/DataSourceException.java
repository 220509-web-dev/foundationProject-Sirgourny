package com.revature.foundations.utils.exceptions;


public class DataSourceException extends RuntimeException {

        // generate a constructor giving it a "String, cause:Throwable)
    public DataSourceException(String message, Throwable cause) {
        super(message, cause);
    }

}