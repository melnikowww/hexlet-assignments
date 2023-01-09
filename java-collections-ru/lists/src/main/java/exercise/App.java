package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
public class App {
    public static boolean scrabble(String symbols, String word) {
        List<String> symb = new ArrayList<>();
        List<String> wordl = new ArrayList<>();
        for (char item: symbols.toCharArray()) {
            symb.add(Character.toString(item));
        }
        for (char item: word.toCharArray()) {
            wordl.add(Character.toString(item));
        }
        for (String item: wordl) {
            if (!symb.contains(item.toLowerCase())) {
                return false;
            } else {
                symb.remove(item);
                continue;
            }
        }
        return true;
    }
}
//END
