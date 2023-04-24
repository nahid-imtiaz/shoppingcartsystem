package edu.miu.shoppingcartsystem.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/")
public class HeartbeatController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String sayHello() {
        return "Hello";
    }
}
