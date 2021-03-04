package se2.kogler.einzelbsp;

import android.util.Log;

public class Calculation implements Runnable {

    // 11702050%7 = 3; ==> digit-sum and binary conversion

    private int matrnr;
    private String result;

    public Calculation(int matrnr) {
        this.matrnr = matrnr;
    }

    @Override
    public void run() {
        this.result = this.getBinaryString(this.getDigitSum(this.matrnr));
        Log.i("calculation", "Calc result: " + this.result);
    }

    /**
     * Sums all digits of a given number
     * @param num input number
     * @return sum of all digits
     */
    private int getDigitSum(int num) {
        int sum = 0;

        while(num > 0) {
            sum += num % 10;
            num = num / 10;
        }

        return sum;
    }

    /**
     * Converts a given integer into its binary-representation.
     * @param num number to be converted
     * @return binary string representation
     */
    private String getBinaryString(int num) {
        StringBuilder bin = new StringBuilder();

        while(num > 0) {
            bin.append(num%2);
            num = num/2;
        }

        return bin.reverse().toString();
    }

    public String getResult() {
        return result;
    }
}
