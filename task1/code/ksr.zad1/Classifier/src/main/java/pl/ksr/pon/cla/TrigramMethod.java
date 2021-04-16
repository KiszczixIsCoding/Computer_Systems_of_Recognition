package pl.ksr.pon.cla;

import java.util.ArrayList;
import java.util.List;

public class TrigramMethod {

    public static final int N = 3;

    public static double calculateSimilarity(String firstWord, String secondWord) {
        int maxLength = getMaxLength(firstWord.length(), secondWord.length());

        List<String> firstWordSubstrings = getSubstrings(firstWord);
        List<String> secondWordSubstrings = getSubstrings(secondWord);

        int substringCounter = 0;
        for (String firstWordSubstring : firstWordSubstrings) {
            if (secondWordSubstrings.contains(firstWordSubstring)) {
                substringCounter++;
            }
        }
        // nie jestem pewny, czy musimy sprawdzać, który podciąg jest dłuższy
        // i sprawdzać ile podciagów z tego krótszego mieści się w dłuzszym,
        // wydaje mi się, że nie ma to znaczenia i rownie dobrze mozna sprawdzic
        // ile podciagow z dluzszego znajduje sie w krotszym, ale to jeszcze do
        // sprawdzenia.

        return substringCounter * 1.0 / (maxLength - N + 1); // ze wzoru na n-gramy
    }

    private static int getMaxLength(int firstWordLength, int secondWordLength) {

        int maxLength = 0;
        if (firstWordLength > secondWordLength) {
            maxLength = firstWordLength;
        } else {
            maxLength = secondWordLength;
        }
        return maxLength;
    }


    private static List<String> getSubstrings(String word) {
        List<String> substrings = new ArrayList<>();
        for (int i = 0; i <= word.length() - N; i++) {
            substrings.add(word.substring(i, i + N));
        }
        return substrings;
    }
}
