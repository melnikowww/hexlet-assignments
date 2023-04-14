package exercise;

import java.util.HashMap;
import java.util.Map;
import static exercise.Utils.*;

// BEGIN
public class FileKV implements KeyValueStorage {

    private String source;

    public FileKV(String source, Map<String, String> data) {
        writeFile(source, serialize(data));
        this.source = source;
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> newData = getMap();
        newData.put(key, value);
        writeFile(source, serialize(newData));
    }

    @Override
    public void unset(String key) {
        Map<String, String> newData = getMap();
        newData.remove(key);
        writeFile(source, serialize(newData));
    }

    @Override
    public String get(String key, String defaultValue) {
        return getMap().getOrDefault(key, "default");
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(getMap());
    }

    public  Map<String, String> getMap() {
        String data = readFile(this.source);
        Map<String, String> unserializedData= unserialize(data);
        return unserializedData;
    }
}
// END
