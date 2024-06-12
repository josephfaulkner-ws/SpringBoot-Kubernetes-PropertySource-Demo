package com.example.springbootdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

    @Value("${spring.application.message}")
    private String message;

    @Value("${spring.application.secret-message}")
    private String secretMessage;

    @GetMapping("/")
    public ModelAndView getIndex(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        modelAndView.addObject("message", message);
        modelAndView.addObject("secretmessage", secretMessage);

        return modelAndView;
    }

}
