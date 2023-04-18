package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag{
    public SingleTag(String tag, Map<String, String> map) {
        this.tag = tag;
        this.attributes = map;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
// END
