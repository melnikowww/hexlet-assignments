package exercise;

import com.sun.nio.sctp.AbstractNotificationHandler;

import java.util.*;

// BEGIN
public class App {
    public static Map<String, String> genDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, String> resultMap = new LinkedHashMap<>(); //как тут это...
        for (Map.Entry<String, Object> item1: map1.entrySet()) {
            if (map2.containsKey(item1.getKey())) {
                if (map2.get(item1.getKey()).equals(map1.get(item1.getKey()))) {
                    resultMap.put(item1.getKey(), "unchanged");
                } else {
                    resultMap.put(item1.getKey(), "changed");
                }
            } else {
                resultMap.put(item1.getKey(), "deleted");
            }
        }
        for (Map.Entry<String, Object> item: map2.entrySet()) {
            if (!map1.containsKey(item.getKey())) {
                resultMap.put(item.getKey(), "added");
            }
        }
        return resultMap;
    }
}
//END
