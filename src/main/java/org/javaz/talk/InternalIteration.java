package org.javaz.talk;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class InternalIteration {

    public static void main(String... args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        numbers.forEach(System.out::println);
    }
}
