package pl.ksr.pon.dao;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Stoplist {
    private static List<String> dictionary = Arrays.asList(
            "the", "to", "and", "of", "a", "in", "i", "that", "you", "it",
            "is", "for", "on", "was", "he", "with", "this", "as", "not", "we",
            "be", "have", "are", "n't", "but", "at", "they", "do", "what", "his",
            "from", "by", "or", "she", "my", "all", "an", "there", "so", "her",
            "about", "me", "one", "had", "if", "your", "can", "who", "no", "out",
            "has", "their", "were", "like", "just", "would", "up", "when", "more", "will",
            "know", "said", "did", "been", "people", "get", "him", "time", "them", "some",
            "how", "now", "which", "could", "think", "than", "our", "into", "other", "right",
            "here", "well", "new", "then", "because", "go", "see", "back", "only", "these",
            "over", "going", "us", "also", "two", "first", "its", "even", "good", "way",
            "v", "ct");


    public static String removeMostPopularWordsFromString(String content) {
        String[] wordsArray = content.split("\\s+");
        List<String> wordsList = new ArrayList<>(Arrays.asList(wordsArray));

        for (String popularWord : dictionary) {
            // small letters
            wordsList.removeAll(Collections.singleton(popularWord));

            char[] charArray = popularWord.toCharArray();
            charArray[0] = Character.toUpperCase(charArray[0]);
            popularWord = String.valueOf(charArray);

            // first capital letter
            wordsList.removeAll(Collections.singleton(popularWord));
        }

        return StringUtils.join(wordsList, " ");
    }

    public static void convertToFirstCapital() {
        for (String word : dictionary) {
            char[] charArray = word.toCharArray();
            charArray[0] = Character.toUpperCase(charArray[0]);
            word = Arrays.toString(charArray);
        }
    }
}
