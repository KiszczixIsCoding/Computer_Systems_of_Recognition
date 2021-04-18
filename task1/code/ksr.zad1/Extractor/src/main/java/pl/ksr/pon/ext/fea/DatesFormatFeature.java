package pl.ksr.pon.ext.fea;

import pl.ksr.pon.ext.TextFeature;
import java.util.*;
import java.util.regex.Pattern;

public class DatesFormatFeature extends Feature implements TextFeature {

    public DatesFormatFeature(boolean isSelected) {
        super(isSelected);
    }

    public void extract(String mainContent, String comparingContent) {
        featureValue = 0;
    }

    @Override
    public String extractTextFeature(String content) {

        String shortMonths = "(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)";
        String months = "(?:January|February|March|April|May|June|July|August|September|October|November|December)";
        String numericMonths = "(?:[1-9]|1[0-2])";
        String shortDays = "(?:[1-9]|[1-2][0-9]|3[0-1])";
        String shortYears = "([0-9][0-9])";
        String years = "([1-2][0-9][0-9][0-9])";
        String end = "(?:\\s+|,\\s+|\\.\\s+)";
        List<Pattern> patterns = new ArrayList<>();

        // Patterns for separators: '.', '-', '/'
        for (String separator : Arrays.asList("/", "-")) {
            patterns.add(Pattern.compile(numericMonths + separator + shortDays + separator + shortYears));
        }

        patterns.add(Pattern.compile(months + " " + shortDays + ", " + years + end));
        patterns.add(Pattern.compile(months + " " + shortDays + end));
        patterns.add(Pattern.compile(shortMonths + " " + shortDays + ", " + years + end));
        patterns.add(Pattern.compile(months + " " + shortDays + end));
        patterns.add(Pattern.compile(months + " " + years + end));
        patterns.add(Pattern.compile(shortMonths + " " + shortDays + end));
        patterns.add(Pattern.compile(shortDays + " " + months + end));
        patterns.add(Pattern.compile(shortDays + " " + months + " " + years + end));
        patterns.add(Pattern.compile(shortDays + "th" + months + " " + years + end));
        patterns.add(Pattern.compile(shortDays + "th of " + months + " " + years + end));
        patterns.add(Pattern.compile(shortDays + "th of " + months + ", " + years + end));


        Map<String, Integer> patternsMap = new HashMap<>();
        for (Pattern pattern : patterns) {
            if (pattern == patterns.get(3)) {
                int prevCount = patternsMap.get(patterns.get(2).toString());
                patternsMap.put(pattern.toString(), (int)pattern.matcher(content).results().count() - prevCount);
            } else if (pattern == patterns.get(5)) {
                int prevCount = patternsMap.get(patterns.get(4).toString());
                patternsMap.put(pattern.toString(), (int)pattern.matcher(content).results().count() - prevCount);
            } else {
                patternsMap.put(pattern.toString(), (int)pattern.matcher(content).results().count());
            }
        }


        return null;
    }
}
