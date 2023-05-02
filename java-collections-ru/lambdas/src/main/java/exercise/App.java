package exercise;

import java.util.Arrays;

// BEGIN
public class App {
    public static String[][] enlargeArrayImage(String[][] image) {
        List<List<String>> imageList = new ArrayList<>();
        List<String> subImageList = new ArrayList<>();
        for (String[] item: image) {
            for (String subItem: item) {
                subImageList.add(item);
            }
            imageList.add(subImageList);
        }
        imageList.stream()
            .forEach(d -> System.out.println(d));
    }

}
// END
