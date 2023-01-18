package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> actual1 = App.take(numbers1, 2);
        List<Integer> expected1 = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            expected1.add(numbers1.get(i));
        }
        System.out.println("actual1: " + actual1);
        System.out.println("exp1: " + expected1);
        assertThat(actual1).isEqualTo(expected1);

        List<Integer> numbers2 = new ArrayList<>(Arrays.asList());
        List<Integer> actual2 = App.take(numbers2, 3);
        List<Integer> expected2 = new ArrayList<>();
        System.out.println("actual2: " + actual2);
        System.out.println("exp2: " + expected2);
        assertThat(actual2).isEqualTo(expected2);

        List<Integer> numbers3 = new ArrayList<>(Arrays.asList(7, 5, 3));
        List<Integer> actual3 = App.take(numbers3, 5);
        List<Integer> expected3 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            expected3.add(numbers3.get(i));
        }
        System.out.println("actual3: " + actual3);
        System.out.println("exp3: " + expected3);
        assertThat(actual3).isEqualTo(expected3);
        // END
    }
}
