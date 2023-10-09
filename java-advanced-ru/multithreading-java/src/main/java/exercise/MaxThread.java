package exercise;

// BEGIN
public class MaxThread extends Thread{

    public int[] numbers;
    public int result;

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        int max = numbers[0];
        for (int number : numbers) {
            if (number > max) {
                max = number;
            }
        }
        result = max;
    }

    public int getResult() {
        return this.result;
    }
}
// END
