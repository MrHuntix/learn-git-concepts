package com.pun.poc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(path = "/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("<h3>welcome</h3>");
    }

    @GetMapping(path = "/admin")
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok("<h3>welcome admin</h3>");
    }

    @GetMapping(path = "/user")
    public ResponseEntity<String> user() {
        return ResponseEntity.ok("<h3>welcome user</h3>");
    }

    @GetMapping(path = "/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Welcome cors");
    }

    @GetMapping(path = "/no")
    public ResponseEntity<String> test2() {
        return ResponseEntity.ok("Welcome no cors");
    }
}
