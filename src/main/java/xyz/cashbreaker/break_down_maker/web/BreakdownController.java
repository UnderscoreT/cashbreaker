package xyz.cashbreaker.break_down_maker.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class BreakdownController {

    public static class CurrencyInfo {
        private final String symbol;
        private final List<Integer> denominations;

        public CurrencyInfo(String symbol, List<Integer> denominations) {
            this.symbol = symbol;
            this.denominations = denominations;
        }

        public String getSymbol() {
            return symbol;
        }

        public List<Integer> getDenominations() {
            return denominations;
        }
    }

    private static final Map<String, CurrencyInfo> currencyMap = new HashMap<>();

    static {
        currencyMap.put("usd", new CurrencyInfo("$", List.of(100, 50, 20, 10, 5, 2, 1)));
        currencyMap.put("cad", new CurrencyInfo("CA$", List.of(100, 50, 20, 10, 5, 2, 1)));
        currencyMap.put("eur", new CurrencyInfo("€", List.of(500, 200, 100, 50, 20, 10, 5)));
        currencyMap.put("gbp", new CurrencyInfo("£", List.of(50, 20, 10, 5, 2, 1)));
        currencyMap.put("jpy", new CurrencyInfo("¥", List.of(10000, 5000, 2000, 1000, 500, 100)));
        currencyMap.put("aud", new CurrencyInfo("A$", List.of(100, 50, 20, 10, 5, 2, 1)));
        currencyMap.put("cny", new CurrencyInfo("¥", List.of(100, 50, 20, 10, 5, 1)));
        currencyMap.put("inr", new CurrencyInfo("₹", List.of(2000, 500, 200, 100, 50, 20, 10, 5)));
        currencyMap.put("rub", new CurrencyInfo("₽", List.of(5000, 1000, 500, 100, 50, 10)));
        currencyMap.put("zar", new CurrencyInfo("R", List.of(200, 100, 50, 20, 10, 5)));
        currencyMap.put("brl", new CurrencyInfo("R$", List.of(200, 100, 50, 20, 10, 5, 2)));
        currencyMap.put("zwd", new CurrencyInfo("Z$", List.of(10000, 5000, 2000, 1000, 500, 200, 100, 50, 20, 10)));
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/select-currency")
    public String selectCurrencyPage() {
        return "select-currency";
    }

    @GetMapping("/breakdown-with-currency")
    public String showFormWithCurrency(@RequestParam(required = false) String currency, Model model) {
        CurrencyInfo info = currencyMap.getOrDefault(currency != null ? currency.toLowerCase() : "usd", currencyMap.get("usd"));

        model.addAttribute("denominations", info.getDenominations());
        model.addAttribute("symbol", info.getSymbol());
        model.addAttribute("currency", currency != null ? currency : "usd");

        return "breakdown-form-with-currency";
    }

    @PostMapping("/breakdown-with-currency")
    public String calculateBreakdown(@RequestParam Map<String, String> params, Model model) {
        String currency = params.getOrDefault("currency", "usd");
        CurrencyInfo info = currencyMap.getOrDefault(currency.toLowerCase(), currencyMap.get("usd"));

        List<Integer> denominations = info.getDenominations();
        double total = 0;

        for (Integer denom : denominations) {
            String countStr = params.get(String.valueOf(denom));
            int count = (countStr != null && !countStr.isEmpty()) ? Integer.parseInt(countStr) : 0;
            total += denom * count;
        }

        model.addAttribute("total", total);
        model.addAttribute("denominations", denominations);
        model.addAttribute("symbol", info.getSymbol());
        model.addAttribute("currency", currency);

        return "breakdown";
    }
}
