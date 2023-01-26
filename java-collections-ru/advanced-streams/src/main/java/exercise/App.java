package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
public class App {
    public static String getForwardedVariables(String data) {
        String[] dataArr = data.split("\n");
        List<String> dataList1 = new ArrayList<>();
        for (String item:dataArr) {
            if (item.contains("X_FORWARDED_")) {
                dataList1.add(item);
            }
        }
        String result;
        List<String> res1 = new ArrayList<>();
        res1 = dataList1.stream()
            .filter(s -> s.startsWith("environment="))
            .map(x -> x.replaceAll("environment=\"", ""))
            .map(x -> x.replaceAll("\"", ""))
            .map(x -> {
                List<String> xArr = new ArrayList<>();
                String res = "";
                String[] xArr1 = x.split(",");
                for (String item: xArr1) {
                    if (item.contains("X_FORWARDED_")) {
                        xArr.add(item);
                    }
                }
                res = String.join(",", xArr);
                return res;
            })
            .map(x -> x.replaceAll("X_FORWARDED_", ""))
            .toList();
        result = String.join(",", res1);
        return result;
    }
}
//END
