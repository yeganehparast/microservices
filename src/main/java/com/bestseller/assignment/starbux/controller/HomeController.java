package com.bestseller.assignment.starbux.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * This controller redirects the requests to the UI interface
 */
@Controller
@ApiIgnore
public class HomeController
{

    @RequestMapping("/")
    public String home()
    {
        return "redirect:swagger-ui.html";
    }

}
