package com.example.VideoTest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/VideoTest")
public class VideoTestController {

    @RequestMapping("/home")
    public String home(){
        return "Home";
    }

}
