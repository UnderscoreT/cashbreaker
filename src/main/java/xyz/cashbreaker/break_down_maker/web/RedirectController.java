package xyz.cashbreaker.break_down_maker.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class RedirectController {

    @RequestMapping("/**")
    public void redirectBasedOnDomain(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String host = request.getHeader("host");

        if (host != null && host.contains("cashbreaker.sizafuel.xyz")) {
            String path = request.getRequestURI();
            String query = request.getQueryString();
            String targetUrl = "https://www.breakmycash.online" + path;
            if (query != null) {
                targetUrl += "?" + query;
            }
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            response.setHeader("Location", targetUrl);
        }

        else if (host != null && host.equals("breakmycash.online")) {
            String path = request.getRequestURI();
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            response.setHeader("Location", "https://www.breakmycash.online" + path);
        }

        // ✅ Default fallback
        else {
            request.getRequestDispatcher("/").forward(request, response);
        }
    }
}
