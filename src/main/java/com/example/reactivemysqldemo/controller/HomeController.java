package com.example.reactivemysqldemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HomeController {

    private String text = "Welcome to the bookstore demo REST API";

    @GetMapping("/")
    public Mono<String> home(){
        return Mono.just(text);
    }
}
