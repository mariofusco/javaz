package org.javaz;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.sumBy;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

import static java.util.stream.Collector.Characteristics.*;

public class StreamsTest {

    List<Dish> menu = Arrays.asList( new Dish("pork", false, 800, Dish.Type.meat),
                                     new Dish("beef", false, 700, Dish.Type.meat),
                                     new Dish("chicken", false, 400, Dish.Type.meat),
                                     new Dish("french fries", true, 530, Dish.Type.other),
                                     new Dish("rice", true, 350, Dish.Type.other),
                                     new Dish("season fruit", true, 120, Dish.Type.other),
                                     new Dish("pizza", true, 550, Dish.Type.other),
                                     new Dish("prawns", false, 400, Dish.Type.fish),
                                     new Dish("salmon", false, 450, Dish.Type.fish));

    @Test
    public void testMax() {
        //Dish fattest = menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        Comparator<Dish> dishCaloriesComparator = Comparator.comparing(Dish::getCalories);
        BinaryOperator<Dish> fatterOf = BinaryOperator.maxBy(dishCaloriesComparator);
        Dish fattest = menu.stream().collect(reducing(fatterOf));
        assertEquals("pork", fattest.getName());
    }

    @Test
    public void testSum() {
        long totalCalories = menu.stream().collect(sumBy(f -> new Long(f.getCalories())));
        assertEquals(4300, totalCalories);
    }

    @Test
    public void testSumWithReducing() {
        //int totalCalories = menu.stream().collect(reducing(0, f -> f.getCalories(), (Integer i, Integer j) -> i + j));
        //int totalCalories = menu.stream().collect(reducing(0, f -> f.getCalories(), Integer::sum));
        int totalCalories = menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
        assertEquals(4300, totalCalories);
    }

    @Test
    public void testPrimeNumbers() {
        List<Integer> primes =
                Stream.iterate(2, i -> i + 1).limit(1000)
                              .filter(n -> Stream.iterate(2, i -> i + 1)
                                                 .limit((long) Math.floor(Math.sqrt((double) n)) - 1)
                                                 .noneMatch(i -> n % i == 0))
                              .collect(toList());
        System.out.println(primes);
    }

    @Test
    public void testPrimeNumbersPartition() {
        Map<Boolean, List<Integer>> primes =
                Stream.iterate(2, i -> i + 1).limit(1000)
                             .collect(partitioningBy(n -> Stream.iterate(2, i -> i + 1)
                                                                .limit((long) Math.floor(Math.sqrt((double) n)) - 1)
                                                                .noneMatch(i -> n % i == 0)));
        System.out.println(primes);
    }

    @Test
    public void testMemoizedPrimeNumbersPartition() {
        Map<Boolean, List<Integer>> primes =
                Stream.iterate(2, i -> i + 1).limit(1000)
                             .collect(new MemoizedPrimeNumbersCollector());
        System.out.println(primes);
    }

    @Test
    public void testMemoizedPrimeNumbersPartition2() {
        Map<Boolean, List<Integer>> primes =
                Stream.iterate(2, i -> i + 1).limit(1000)
                             .collect(
                                     () -> new HashMap<Boolean, List<Integer>>() {{
                                         put(true, new ArrayList<Integer>());
                                         put(false, new ArrayList<Integer>());
                                     }},
                                     (acc, candidate) -> {
                                         acc.get( isPrime(acc.get(true), candidate) ).add(candidate);
                                     },
                                     Map::putAll);
        System.out.println(primes);
    }

    private static boolean isPrime(List<Integer> primes, Integer candidate) {
        return takeWhile(primes, i -> i <= Math.floor(Math.sqrt((double) candidate)))
                .stream()
                .noneMatch(i -> candidate % i == 0);
    }

    private static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for (A item : list) {
            if (!p.test(item)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }

    public static class MemoizedPrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>> {

        @Override
        public Supplier<Map<Boolean, List<Integer>>> resultSupplier() {
            return () -> new HashMap<Boolean, List<Integer>>() {{
                put(true, new ArrayList<Integer>());
                put(false, new ArrayList<Integer>());
            }};
        }

        @Override
        public BiFunction<Map<Boolean, List<Integer>>, Integer, Map<Boolean, List<Integer>>> accumulator() {
            return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
                acc.get( isPrime(acc.get(true), candidate) ).add(candidate);
                return acc;
            };
        }

        private boolean isPrime(List<Integer> primes, Integer candidate) {
            return takeWhile(primes, i -> i <= Math.floor(Math.sqrt((double) candidate)))
                    .stream()
                    .noneMatch(i -> candidate % i == 0);
        }

        private <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
            int i = 0;
            for (A item : list) {
                if (!p.test(item)) {
                    return list.subList(0, i);
                }
                i++;
            }
            return list;
        }

        @Override
        public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
            return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
                map1.putAll(map2);
                return map1;
            };
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(STRICTLY_MUTATIVE));
        }
    }

    public static class Dish {
        private final String name;
        private final boolean vegeterian;
        private final int calories;
        private final Type type;

        public Dish(String name, boolean vegeterian, int calories, Type type) {
            this.name = name;
            this.vegeterian = vegeterian;
            this.calories = calories;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public boolean isVegeterian() {
            return vegeterian;
        }

        public int getCalories() {
            return calories;
        }

        public Type getType() {
            return type;
        }

        public enum Type { meat, fish, other }
    }
}
