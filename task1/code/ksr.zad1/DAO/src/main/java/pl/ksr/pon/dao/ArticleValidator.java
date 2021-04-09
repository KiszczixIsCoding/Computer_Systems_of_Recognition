package pl.ksr.pon.dao;

import org.apache.commons.lang3.StringUtils;
import pl.ksr.pon.ext.ClassifiedPlaces;

public class ArticleValidator {

    public String getPlace(String line) {
        // Condition for more than one place
        if (StringUtils.countMatches(line, "<D>") != 1) {
            return null;
        }

        // Check if one of the places from enum is in the line
        for (ClassifiedPlaces place: ClassifiedPlaces.values()) {
            if (StringUtils.contains(line, place.name())) {
                return place.name();
            }
        }
        return null;
    }

    public boolean checkOpenMarkup(String line, Markups markup) {
        if (markup == Markups.REUTERS) {
            return StringUtils.contains(line, "<" + markup.name());
        } else {
            return StringUtils.contains(line, "<" + markup.name() + ">");
        }
    }

    public boolean checkCloseMarkup(String line, Markups markup) {
        return StringUtils.contains(line, "</" + markup.name() + ">");
    }

}
