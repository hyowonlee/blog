package com.cro.blog.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {

    @GetMapping("/test")
    public String Hello()
    {
        System.out.println("test/SampleController");
        return "test";
    }
}
