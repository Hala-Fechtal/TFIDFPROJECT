package tfidf.utils;

import java.util.HashMap;
import java.util.Map;

public class TextCleaner {
    public static Map<String, Integer> tokenizeAndCount(String text) {
        Map<String, Integer> wordCounts = new HashMap<>();
        String[] words = text.toLowerCase()
                .replaceAll("\\p{Punct}", "")
                .split("\\s+");

        for (String word : words) {
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }

        return wordCounts;
    }
}