package com.nd.electronic.web.MTechDistributions.exceptionUtils;

public class ResouceNotFoundException extends RuntimeException {

    public ResouceNotFoundException() {

        super("Resouece is not found");
    }
    public ResouceNotFoundException(String message){
        super(message);
    }

}