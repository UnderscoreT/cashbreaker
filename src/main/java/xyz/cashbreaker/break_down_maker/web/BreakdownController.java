package xyz.cashbreaker.break_down_maker.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class BreakdownController {

    @GetMapping("/")
    public String index() {
//        return "breakdown";
//        return "fun";
//        return "auto";
        return "select-currency";
    }
    @GetMapping("/select-currency")
    public String selectCurrencyPage() {
        return "select-currency";
    }

    @GetMapping("/breakdown-with-currency")
    public String showFormWithCurrency(@RequestParam String currency, Model model) {
        List<Integer> denominations;
        String symbol;

        if ("usd".equalsIgnoreCase(currency)) {
            denominations = List.of(100, 50, 20, 10, 5, 2, 1);
            symbol = "$";
        } else if ("zwd".equalsIgnoreCase(currency) || "zw".equalsIgnoreCase(currency)) {
            denominations = List.of(10000, 5000, 2000, 1000, 500, 200, 100);
            symbol = "Z$";
        } else {
            denominations = List.of();
            symbol = "";
        }

        model.addAttribute("denominations", denominations);
        model.addAttribute("symbol", symbol);
        model.addAttribute("currency", currency);


        return "breakdown-form-with-currency";
    }



    @PostMapping("/breakdown-with-currency")
    public String calculateBreakdown(@RequestParam Map<String, String> params, Model model) {
        List<Integer> denominations = List.of(100, 50, 20, 10, 5, 2, 1);
        double total = 0;

        for (Integer denom : denominations) {
            String countStr = params.get(String.valueOf(denom));
            int count = (countStr != null && !countStr.isEmpty()) ? Integer.parseInt(countStr) : 0;
            total += denom * count;
        }


        model.addAttribute("total", total);
        model.addAttribute("denominations", denominations);
        return "breakdown";
    }
}
