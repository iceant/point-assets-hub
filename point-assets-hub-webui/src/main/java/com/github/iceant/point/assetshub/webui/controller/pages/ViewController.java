package com.github.iceant.point.assetshub.webui.controller.pages;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {
    @RequestMapping(path = {"", "/index", "/home", "/"})
    public ModelAndView index() {
        return new ModelAndView("pages/index.html");
    }

    @GetMapping(path = {"/login"}, produces = {MediaType.TEXT_HTML_VALUE})
    public ModelAndView login() {
        return new ModelAndView("pages/login.html");
    }
}
