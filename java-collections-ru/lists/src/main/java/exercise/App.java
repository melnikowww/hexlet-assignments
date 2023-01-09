package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
public class App {
    public static boolean scrabble(String symbols, String word) {
        List<String> listSymbols = new ArrayList<String>();
        List<String> listWord = new ArrayList<String>();
        boolean result = true;
        for (int item: listWord) {
            if (!listSymbols.contains(item)) {
                return false;
            }
        }
        return result;
    }
}
//END
