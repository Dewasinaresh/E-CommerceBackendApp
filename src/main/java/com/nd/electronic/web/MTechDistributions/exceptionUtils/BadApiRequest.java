package com.nd.electronic.web.MTechDistributions.exceptionUtils;

public class BadApiRequest extends RuntimeException{
    public BadApiRequest(){
        super("Bad Request");
    }
   public BadApiRequest(String msg){
        super(msg);
    }
}
