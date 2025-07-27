package xyz.cashbreaker.break_down_maker.web;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SitemapController {

    @GetMapping(value = "/sitemap.xml", produces = "application/xml")
    @ResponseBody
    public Resource getSitemap() {
        return new ClassPathResource("static/sitemap.xml");
    }
}
