package exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> objects, int count) {
        Collections.sort(objects, (h1, h2) -> h1.compareTo(h2));
        int counter = 0;
        List<String> result = new ArrayList<>();
        for (Home home:objects) {
            result.add(home.toString());
            counter++;
            if (counter == count) {break;}
        }
        return result;
    }
}
// END
