package ua.chemerys.currencyexchanger.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

@Component
public class RatesParser {

    @Scheduled(fixedRate = 5000)
    public JSONObject getCurrencyRange() {

        JSONObject jsonObject = null;

        try {

            URL url = new URL("https://developers.paysera.com/tasks/api/currency-exchange-rates");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder inline = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    inline.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parse = new JSONParser();
                JSONObject data_obj = (JSONObject) parse.parse(inline.toString());

                jsonObject = (JSONObject) data_obj.get("rates");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public List<String> getListCurrenciesCodes() {

        return (List<String>) getCurrencyRange().keySet().stream().sorted().toList();
    }

    public BigDecimal getCurrencyRate(String currencyCode) {
        BigDecimal rate;

        if (currencyCode.equals("EUR")) {
            rate = BigDecimal.valueOf(1);
        } else {
            rate = BigDecimal.valueOf((Double) getCurrencyRange().get(currencyCode));
        }

        return rate;
    }
}
