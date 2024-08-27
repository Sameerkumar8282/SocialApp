package com.MySocial.SocialApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    //to retrieve data from database
    @GetMapping("/home")
    public String homeControllerHandler(){
        return "This is Home controller";
    }
}
