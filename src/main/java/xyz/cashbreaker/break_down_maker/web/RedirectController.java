package xyz.cashbreaker.break_down_maker.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class RedirectController {

    @RequestMapping("/**/{path:[^\\.]*}")
    public void redirectBasedOnDomain(HttpServletRequest request,
                                      HttpServletResponse response)
            throws IOException, ServletException {
        String host = request.getHeader("Host");
        String uri  = request.getRequestURI();
        String query = request.getQueryString();

        boolean isOldDomain = host != null && (
                host.contains("cashbreaker.sizafuel.xyz") ||
                        host.equals("breakmycash.online") ||
                        host.equals("www.breakmycash.online")
        );

        if (isOldDomain) {
            String target = "https://www.breakmycash.online" + uri
                    + (query != null ? "?"+query : "");
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            response.setHeader("Location", target);
            return;
        }

        // default: forward to your index controller (which serves index.html)
        request.getRequestDispatcher("/").forward(request, response);
    }
}
