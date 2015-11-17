package com.ixxus.associationFilter.objects.exceptions;

/**
 * Created by Ixxus on 11/17/2015.
 */
public class MethodNotImplementedException extends Exception {
    public MethodNotImplementedException(){
        super();
    }
    public MethodNotImplementedException(String message) {
        super(message);
    }
    public MethodNotImplementedException(String message, Throwable t) {
        super(message, t);
    }
}
