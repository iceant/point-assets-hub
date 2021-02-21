package com.github.iceant.point.assetshub.webui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {
    @RequestMapping(path = {"","/index", "/home", "/"})
    public ModelAndView index(){
        return new ModelAndView("pages/index.html");
    }
}
