package com.ixxus.associationFilter.objects.exceptions;

/**
 * Created by Ixxus on 11/17/2015.
 */
public class MissingParameterException extends Exception {
    public MissingParameterException(){
        super();
    }
    public MissingParameterException(String message) {
        super(message);
    }
    public MissingParameterException(String message, Throwable t) {
        super(message, t);
    }
}
