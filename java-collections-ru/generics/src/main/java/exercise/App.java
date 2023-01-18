package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
public class App {
    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> where) {
        List<Map<String, String>> result = new ArrayList<>();
//        for (Map.Entry<String, String> where1: where.entrySet()) {
//            for (Map<String, String> item: books) {
//                if (where1.getValue() == item.get(where1.getKey())) {
//                    result.add(item);
//                }
//            }
//        }
        for (Map<String, String> item: books) {
            int rulesCounter = 0;
            for (Map.Entry<String, String> where1: where.entrySet()) {
                if (item.get(where1.getKey()) != where1.getValue()) {
                    break;
                } else {
                    rulesCounter++;
                    if (rulesCounter == where.size()) {
                        result.add(item);
                    }
                }
            }
        }
        return result;
    }
}
//END
