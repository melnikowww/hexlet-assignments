package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {
        MaxThread thread1 = new MaxThread(numbers);
        MinThread thread2 = new MinThread(numbers);

        Map<String, Integer> result = new HashMap<>();

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();

        } catch (InterruptedException e) {
            System.out.println("Поток был прерван");
        }


        result.put("min", thread2.getResult());
        result.put("max", thread1.getResult());

        return result;
    }
    // END
}
