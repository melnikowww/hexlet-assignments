package exercise;

import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
public class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        return users.stream()
            .filter(usr -> usr.get("gender").equals("male"))
            .sorted((usr1, usr2) -> usr1.get("birthday").compareTo(usr2.get("birthday")))
            .map(usr -> usr.get("name"))
            .collect(Collectors.toList());
    }
}
// END
