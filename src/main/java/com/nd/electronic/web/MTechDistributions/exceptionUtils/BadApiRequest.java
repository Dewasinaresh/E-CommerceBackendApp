package com.nd.electronic.web.MTechDistributions.exceptionUtils;

public class BadApiRequest extends RuntimeException{
    public BadApiRequest(){
        super("Bad Request");
    }
    BadApiRequest(String msg){
        super(msg);
    }
}
