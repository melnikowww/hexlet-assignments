package exercise;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

// BEGIN
public class PairedTag extends Tag{

    public PairedTag(String tag, Map<String, String> atr, String body, List<Tag> children) {
        this.tag = tag;
        this.attributes = atr;
        this.body = body;
        this.children = children;
    }

    @Override
    public String toString() {
        String newChildren = children.toString()
            .replace("[", "")
            .replace("]", "")
            .replace(", ", "");
        return super.toString() + body + newChildren + "</" + this.tag + ">";
    }
}
// END
