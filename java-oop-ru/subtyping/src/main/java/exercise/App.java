package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage map) {
        KeyValueStorage result = new InMemoryKV(map.toMap());
        for (Map.Entry<String, String> item: result.toMap().entrySet()) {
            map.set(item.getValue(), item.getKey());
            map.unset(item.getKey());
        }
    }
}
// END
