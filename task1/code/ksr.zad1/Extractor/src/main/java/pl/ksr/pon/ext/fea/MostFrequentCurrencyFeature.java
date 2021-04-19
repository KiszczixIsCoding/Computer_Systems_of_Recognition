package pl.ksr.pon.ext.fea;

import org.apache.commons.lang3.StringUtils;
import pl.ksr.pon.ext.TextFeature;
import pl.ksr.pon.ext.TrigramMethod;
import pl.ksr.pon.ext.dic.CurrencyDictionary;

import java.util.*;

public class MostFrequentCurrencyFeature extends Feature implements TextFeature {

    public MostFrequentCurrencyFeature(boolean isSelected) {
        super(isSelected);
    }

    public void extract(String content, String comparingContent) {
        String mainContent = extractTextFeature(content);
        String compContent = extractTextFeature(comparingContent);
        featureValue = TrigramMethod.calculateSimilarity(mainContent, compContent);    }

    @Override
    public String extractTextFeature(String content) {
        if (content == null) {
            return null;
        }
        Map<String, Integer> currenciesMap = new HashMap<>();
        List<String> currenciesDictionary = new CurrencyDictionary().getDictionary();

        for (String currency : currenciesDictionary) {

            char[] charArray = currency.toCharArray();
            charArray[0] = Character.toUpperCase(charArray[0]);
            String upperCurrency = String.valueOf(charArray);


            int count = 0;
            if (currency.equals("sin")) {
                String[] wordsList = content.split("\\s+");
                List<String> words = new ArrayList<>(Arrays.asList(wordsList));
                count = Collections.frequency(words, "sin") + Collections.frequency(words, "Sin");
            } else {
                count = StringUtils.countMatches(content, currency) + StringUtils.countMatches(content, upperCurrency);
            }
            if (currency.equals("dlr")) {
                currenciesMap.put("dollar", currenciesMap.get("dollar"));
            } else {
                currenciesMap.put(currency, count);
            }

        }

        // Sorting HashMap
        currenciesMap.entrySet().stream().sorted(Map.Entry.comparingByValue());
        Map.Entry<String, Integer> mostFrequentCurrency = currenciesMap.entrySet().iterator().next();
        return mostFrequentCurrency.getKey();
    }
}
