package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public final class InMemoryKV implements KeyValueStorage {
    private Map<String, String> data = new HashMap<>();

    public InMemoryKV(Map<String, String> data) {
        Map<String, String> copyMap = new HashMap<>();
        for (String key: data.keySet()) {
            copyMap.put(key, data.get(key));
        }
        this.data.putAll(copyMap);
    }
    @Override
    public void set(String key, String value) {
        this.data.put(key, value);
    }
    @Override
    public void unset(String key) {
        this.data.remove(key);
    }
    @Override
    public String get(String key, String defaultValue) {
        return this.data.getOrDefault(key, defaultValue);
    }
    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(data);
    }
}
// END
