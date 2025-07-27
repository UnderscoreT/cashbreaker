package xyz.cashbreaker.break_down_maker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping({"/", "/index"})
    public String home() {
        return "index"; // assumes index.html is in src/main/resources/templates/

    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "Hello, world!";
    }

}
