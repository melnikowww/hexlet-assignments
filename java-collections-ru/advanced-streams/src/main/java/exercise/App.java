package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
public class App {
    public static String getForwardedVariables(String data) {
        String[] lines = data.split("\n");
        return Arrays.stream(lines)
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
