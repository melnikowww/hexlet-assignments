package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
public class App {
    public static String getForwardedVariables(String data) {
        String[] lines = data.split("\n");
        List<String> lineList = new ArrayList<>();
        for (String item: lines) {
            lineList.add(item);
        }
        return lineList.stream()
            .filter(x -> x.startsWith("environment"))
            .map(x -> x.replaceAll("environment=", ""))
            .map(x -> x.replaceAll("\"", ""))
            .map(x -> x.split(","))
            .flatMap(Arrays::stream)
            .filter(x -> x.contains("X_FORWARDED_"))
            .map(x -> x.replaceAll("X_FORWARDED_", ""))
            .collect(Collectors.joining(","));
    }
}
//END
