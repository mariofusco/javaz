package org.javaz.sample;

import java.util.*;
import java.util.function.*;

public class InternalIteration {

    public static void main(String... args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        for (int number : numbers) {
            System.out.println(number);
        }

        numbers.forEach(new Consumer<Integer>() {
            public void accept(Integer value) {
                System.out.println(value);
            }
        });

        numbers.forEach((Integer value) -> System.out.println(value));

        numbers.forEach(value -> System.out.println(value));

        numbers.forEach(System.out::println);
    }
}
