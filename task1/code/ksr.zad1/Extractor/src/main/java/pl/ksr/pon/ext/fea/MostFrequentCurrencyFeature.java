package pl.ksr.pon.ext.fea;

import org.apache.commons.lang3.StringUtils;
import pl.ksr.pon.ext.TextFeature;
import pl.ksr.pon.ext.dic.CurrencyDictionary;

import java.util.*;

public class MostFrequentCurrencyFeature extends Feature implements TextFeature {
    private List<String> mostFrequentCurrencies;

    public MostFrequentCurrencyFeature(boolean isSelected) {
        super(isSelected);
    }

    @Override
    public List<String> extract(String content) {
        Map<String, Integer> currenciesMap = new HashMap<>();
        List<String> currenciesDictionary = new CurrencyDictionary().getDictionary();

        for (String currency : currenciesDictionary) {
            String upperCurrency = currency.replaceFirst(
                    String.valueOf(currency.charAt(0)), currency.toUpperCase(Locale.ROOT));

            int count = StringUtils.countMatches(content, currency) + StringUtils.countMatches(content, upperCurrency);

            if (currency.equals("dlr")) {
                currenciesMap.put("dollar", currenciesMap.get("dollar"));
            } else {
                currenciesMap.put(currency, count);
            }

        }

        // Sorting HashMap
        currenciesMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);

        return Arrays.asList("1", "1");
    }
}
