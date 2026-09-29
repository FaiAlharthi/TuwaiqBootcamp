package com.example.packup.Api;

// don't need any annotation, cause it's extend from existing component
public class ApiException extends RuntimeException {
    public ApiException(String message){
        super(message);
    }
}
