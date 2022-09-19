package com.example.demo.Controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "application")
@RestController
@RequestMapping(value = "/")
public class HelloController {

    @GetMapping
    public String getInfo(){
        return "hello";
    }
}
