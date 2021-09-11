package site.askephoenix.restapi.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "/")
public class PageController {
    @GetMapping(value = "")
    public String mainPage(){
        return "pages/main";
    }
    @GetMapping("login")
    public String loginPage(){
        return "pages/login";
    }

    @GetMapping("sign-on")
    public String signOnPage(){
        return "pages/sign-on";
    }
}
