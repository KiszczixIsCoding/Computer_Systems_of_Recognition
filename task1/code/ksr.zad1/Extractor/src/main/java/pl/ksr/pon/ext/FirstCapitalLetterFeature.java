package pl.ksr.pon.ext;

import lombok.Getter;

@Getter
public class FirstCapitalLetterFeature implements NumericalFeature {
    private int firstCapitalLetterCount;

    @Override
    public int extract(String content) {
        int counter = 0;
        String[] wordsList = content.split("\\s+");
        for (String word : wordsList) {
            char firstLetter = word.charAt(0);
            if (firstLetter >= 'A' && firstLetter <= 'Z') {
                counter++;
            }
        }

        //TODO Remove AllCapitalLettersFeature extraction result
        return counter;
    }
}
