package com.hospital.hospital_management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/check")
    public Map<String, String> check() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        response.put("message", "Hospital Management API is running");
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        return response;
    }
}