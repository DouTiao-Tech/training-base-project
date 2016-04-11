package com.darcytech.training.base;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        System.out.println(new Main().fractionToDecimal(0, -5));
        System.out.println(new Main().fractionToDecimal(1, -5));
        System.out.println(new Main().fractionToDecimal(2, 5));
        System.out.println(new Main().fractionToDecimal(1, 3));
        System.out.println(new Main().fractionToDecimal(-1, -2147483648));
    }

    public String fractionToDecimal(int numerator, int denominator) {
        int sign = (numerator < 0 ? -1 : 1) * (denominator < 0 ? -1 : 1);
        long n = Math.abs((long)numerator);
        long d = Math.abs((long)denominator);
        String trueDecimal = toTrueDecimal(n % d, d);
        String result = (sign == -1 && n != 0 ? "-" : "") + Long.toString(n / d);
        if (trueDecimal.length() > 0) {
            result = result + "." + trueDecimal;
        }
        return result;
    }

    static String toTrueDecimal(long numerator, long denominator) {
        Map<Long, Long> positions = new HashMap<>();
        StringBuilder result = new StringBuilder();
        while (numerator > 0 && !positions.containsKey(numerator)) {
            positions.put(numerator, (long)result.length());
            numerator = numerator * 10;
            result.append(numerator / denominator);
            numerator = numerator % denominator;
        }
        if (numerator > 0) {
            result.insert(positions.get(numerator).intValue(), '(').append(')');
        }
        return result.toString();
    }

}
