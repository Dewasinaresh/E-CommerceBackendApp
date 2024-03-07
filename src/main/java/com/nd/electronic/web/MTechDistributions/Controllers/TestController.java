package com.nd.electronic.web.MTechDistributions.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Test")
public class TestController {

    @GetMapping("/112")
    public String Test(){
        return "Welcome to M-Tech  Distributions";
    }

}
