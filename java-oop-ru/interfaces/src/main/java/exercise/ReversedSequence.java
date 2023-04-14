package exercise;

import java.util.Arrays;
import java.util.stream.IntStream;

// BEGIN
public class ReversedSequence implements CharSequence {

    String str;

    @Override
    public String toString() {
        return String.copyValueOf(this.str.toCharArray());
    }

    public ReversedSequence(String data) {
        char[] str = data.toCharArray();
        char[] result = new char[str.length];
        int j = 0;
        for (int i = result.length - 1; i > -1 ; i--) {
            result[i] = str[j];
            j++;
        }
        this.str = String.copyValueOf(result);
    }

    @Override
    public boolean isEmpty() {
        return CharSequence.super.isEmpty();
    }

    @Override
    public IntStream chars() {
        return CharSequence.super.chars();
    }

    @Override
    public IntStream codePoints() {
        return CharSequence.super.codePoints();
    }

    @Override
    public int length() {
        return str.length();
    }

    @Override
    public char charAt(int i) {
        return str.toCharArray()[i];
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return str.substring(i, i1);
    }
}
// END
