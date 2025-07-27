package xyz.cashbreaker.break_down_maker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/index"})
    public String home() {
        return "index"; // assumes index.html is in src/main/resources/templates/
    }
}
