package com.example.demo.home;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/")
    public String index(){
        return "main";
    }
}
