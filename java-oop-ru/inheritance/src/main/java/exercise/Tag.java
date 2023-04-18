package exercise;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Tag {
    protected String tag;
    protected Map<String, String> attributes;
    protected String body;
    protected List<Tag> children;
//    public Tag(String tag, Map<String, String> atrbs){
//        this.tag = tag;
//        this.atributes = atrbs;
//    }
    public String toString() {
        Map<String, String> newAttributes = new LinkedHashMap<>();
        for (Map.Entry<String, String> item: this.attributes.entrySet()) {
            newAttributes.put(item.getKey(), "\"" + item.getValue() + "\"");
        }
        String space;
        if (newAttributes.isEmpty()) {
            space = "";
        } else {
            space = " ";
        }
        String string = "<" + this.tag + space + newAttributes + ">";
        string = string.replace("{", "");
        string = string.replace("}", "");
        string = string.replace(",", "");
        return string;
    };
}
// END
