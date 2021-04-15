package pl.ksr.pon.ext.fea;

import org.apache.commons.lang3.StringUtils;
import pl.ksr.pon.ext.TextFeature;
import pl.ksr.pon.ext.dic.DateDictionary;

import java.util.*;
import java.util.regex.Pattern;

public class DatesFormatFeature implements TextFeature {

    @Override
    public List<String> extract(String content) {

        List<String> separators = Arrays.asList("\\/", "\\.", "-");
        String shortMonths = "(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)";
        String months = "(?:January|February|March|April|May|June|July|August|September|October|November|December)";
        String numericMonths = "(?:0[1-9]|1[0-2])";
        String days = "(?:0[1-9]|[1-2][0-9]|3[0-1])";
        String shortDays = "(?:[1-9]|[1-2][0-9]|3[0-1])";
        String years = "[1-2][0-9][0-9][0-9]";

        List<Pattern> patterns = new ArrayList<>();

        // Patterns for separators: '.', '-', '/'
        for (String separator : Arrays.asList("\\/", "\\.", "-")) {
            patterns.add(Pattern.compile(years + separator + months + separator + days));
            patterns.add(Pattern.compile(months + separator + days + separator + years));
            patterns.add(Pattern.compile(days + separator + months + separator + years));
        }

        patterns.add(Pattern.compile(months + " " + shortDays));
        patterns.add(Pattern.compile(months + " " + years));
        patterns.add(Pattern.compile(shortMonths + " " + shortDays));
        patterns.add(Pattern.compile(shortDays + " " + months));
        patterns.add(Pattern.compile(shortDays + " " + months + " " + years));
        patterns.add(Pattern.compile(shortDays + "th" + months + " " + years));
        patterns.add(Pattern.compile(shortDays + "th of " + months + " " + years));
        patterns.add(Pattern.compile(shortDays + "th of " + months + ", " + years));
        patterns.add(Pattern.compile(months + " " + shortDays + ", " + years));
        patterns.add(Pattern.compile(shortMonths + " " + shortDays + ", " + years));

        Map<String, Integer> patternsMap = new HashMap<>();
        for (Pattern pattern : patterns) {
            patternsMap.put(pattern.toString(), (int)pattern.matcher(content).results().count());

        }

        return null;
    }
}
