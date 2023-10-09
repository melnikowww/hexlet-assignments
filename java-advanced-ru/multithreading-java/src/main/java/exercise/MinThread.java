package exercise;

// BEGIN
public class MinThread extends Thread{

    public int[] numbers;
    public int result;

    public MinThread(int [] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        int min = numbers[0];
        for (int number : numbers) {
            if (number < min) {
                min = number;
            }
        }
        result = min;
    }

    public int getResult() {
        return this.result;
    }
}
// END
