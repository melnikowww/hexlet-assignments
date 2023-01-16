package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static Map getWordCount(String sentence) {
        String[] subString;
        Map<String, Integer> words = new HashMap<>();
        subString = sentence.split(" ");
        for (String item: subString) {
            int newValue;
            if (!words.containsKey(item)) {
                words.put(item, 1);
            } else {
                newValue = words.get(item) + 1;
                words.put(item, newValue);
            }
        }
        words.remove("");
    return words;
    }
    public static String toString(Map words) {
        Map<String, Integer> mapWords = new HashMap<>();
        mapWords.putAll(words);
        if (words.size() == 0) {
            return "{}";
        }
        String result = "{";
        for (Map.Entry<String, Integer> word: mapWords.entrySet()) {
            result = result + "\n" + "  " + word.getKey() + ": " + word.getValue();
        }
        result = result + "\n" + "}";
        return result;
    }
}
//END
