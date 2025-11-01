package com.medcare.medcare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    
    @GetMapping("/")
    public String index() {
        return "forward:/index.html";
    }
    
    @GetMapping("/calendario")
    public String calendario() {
        return "forward:/index.html";
    }
    
    @GetMapping("/medicamentos")
    public String medicamentos() {
        return "forward:/index.html";
    }
}