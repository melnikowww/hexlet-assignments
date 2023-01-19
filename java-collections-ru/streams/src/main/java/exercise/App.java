package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
public class App {
    public static int getCountOfFreeEmails(List<String> emails) {
        List<String> freeEmails = List.of("gmail.com", "yandex.ru", "hotmail.com");
        return (int) emails.stream()
            .filter(data -> data.endsWith(freeEmails.get(0)) || data.endsWith(freeEmails.get(1)) || data.endsWith(freeEmails.get(2)))
            .count();
    }
}
// END
