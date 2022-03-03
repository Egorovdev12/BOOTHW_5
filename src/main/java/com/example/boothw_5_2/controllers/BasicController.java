package com.example.boothw_5_2.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BasicController {

    @Value("${server.port}")
    private int port;

    @Value("${status.public}")
    private boolean status;

    @GetMapping("/hello")
    public String hello() {
        if (status)
            return "Hello from port " + port;
        else
            return "Hello form port [ACCESS DENIED]";
    }
}