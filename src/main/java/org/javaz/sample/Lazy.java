package org.javaz.sample;

import java.util.*;

public class Lazy {

    public static boolean isEven(int number) {
        System.out.println("isEven: " + number);
        return number % 2 == 0;
    }

    public static int doubleIt(int number) {
        System.out.println("doubleIt: " + number);
        return number * 2;
    }

    public static boolean greaterThan5(int number) {
        System.out.println("greaterThan5: " + number);
        return number > 5;
    }

    public static void main(String... args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        // Take only the even numbers, double them and print
        // the first one bigger than 5

        for (int number : numbers) {
            if (number % 2 == 0) {
                int n2 = number * 2;
                if (n2 > 5) {
                    System.out.println(n2);
                    break;
                }
            }
        }

        System.out.println(numbers.stream()
                .filter(Lazy::isEven)
                .map(Lazy::doubleIt)
                .filter(Lazy::greaterThan5)
                .findFirst().get());
    }
}