package com.my.phamacy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormControoler {
    @GetMapping
    public String mainForm(){
        return "main";
    }

    @GetMapping("/output")
    public String outputForm(){
        return "output";
    }
}
