package org.javaz;

import org.junit.Test;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.util.stream.Collectors.*;
import static org.junit.Assert.assertEquals;

import static java.util.stream.Collector.Characteristics.*;

public class StreamsTest {

    @Test
    public void testFlatMap() {
        List<int[]> list =
                Stream.iterate(1, i -> i+1).limit(10)
                        .flatMap(i -> Stream.iterate(1, j -> j+1).limit(10).map(j -> new int[]{i, j}))
                        .filter(pair -> StreamsTest.isPrime(pair[0] + pair[1]))
                        .collect(toList());
        List<String> sList = list.stream().map(Arrays::toString).collect(toList());
        System.out.println(sList);
    }

    public static boolean isPrime(int n) {
        return Stream.iterate(2, i -> i+1).limit((long) Math.sqrt(n)).noneMatch(i -> n % i == 0);
    }

    List<Dish> menu = Arrays.asList( new Dish("pork", false, 800, Dish.Type.MEAT),
                                     new Dish("beef", false, 700, Dish.Type.MEAT),
                                     new Dish("chicken", false, 400, Dish.Type.MEAT),
                                     new Dish("french fries", true, 530, Dish.Type.OTHER),
                                     new Dish("rice", true, 350, Dish.Type.OTHER),
                                     new Dish("season fruit", true, 120, Dish.Type.OTHER),
                                     new Dish("pizza", true, 550, Dish.Type.OTHER),
                                     new Dish("prawns", false, 400, Dish.Type.FISH),
                                     new Dish("salmon", false, 450, Dish.Type.FISH));

    @Test
    public void testMax() {
        Dish fattest = menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).get();
/*
        Comparator<Dish> dishCaloriesComparator = Comparator.comparing(Dish::getCalories);
        BinaryOperator<Dish> fatterOf = BinaryOperator.maxBy(dishCaloriesComparator);
        Dish fattest = menu.stream().collect(reducing(fatterOf)).get();
*/
        assertEquals("pork", fattest.getName());
    }

    @Test
    public void testSum() {
        //int totalCalories = menu.stream().collect(summingInt((dish -> dish.getCalories())));
        int totalCalories = menu.stream().mapToInt(Dish::getCalories).sum();
        assertEquals(4300, totalCalories);
    }

    @Test
    public void testSumWithReducing() {
        int totalCalories = menu.stream().collect(reducing(0, f -> f.getCalories(), (i, j) -> i + j));
        //int totalCalories = menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
        //int totalCalories = menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
        assertEquals(4300, totalCalories);
    }

    @Test
    public void testJoining() {
        //String s = menu.stream().map(Dish::getName).collect(joining());
        //String s = menu.stream().collect(reducing((d1, d2) -> d1.getName() + ", " + d2.getName())).get();
        String s = menu.stream().collect(reducing("", Dish::getName, (s1, s2) -> s1 + s2));
        System.out.println(s);
    }

    @Test
    public void testSummarizing() {
        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);
    }

    @Test
    public void testGroup() {
        //Map<Dish.Type, List<Dish>> types = menu.stream().collect(groupingBy(dish -> dish.getType()));
        Map<Dish.Type, List<Dish>> types = menu.stream().collect(groupingBy(Dish::getType));
        assertEquals(2, types.get(Dish.Type.FISH).size());
        System.out.println(types);
    }

    @Test
    public void testMultiLevelGroup() {
        Map<Dish.Type, Long> typesCount = menu.stream().collect(
                groupingBy(Dish::getType, counting()));

        System.out.println(typesCount);

        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> types = menu.stream().collect(
                groupingBy(Dish::getType,
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.dietetic;
                    else if (dish.getCalories() <= 700) return CaloricLevel.normal;
                    else return CaloricLevel.fat;
                } )));
        System.out.println(types);

        Map<Dish.Type, Set<CaloricLevel>> map = menu.stream().collect(
                groupingBy(Dish::getType,
                        mapping(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.dietetic;
                            else if (dish.getCalories() <= 700) return CaloricLevel.normal;
                            else return CaloricLevel.fat;
                        }, toSet() )));
        System.out.println(map);
    }

    @Test
    public void testFattestByType() {
        Map<Dish.Type, Optional<Dish>> types = menu.stream().collect(
                groupingBy(Dish::getType, reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)));
        System.out.println(types);

        Map<Dish.Type, Dish> types2 = menu.stream().collect(
                groupingBy(Dish::getType, collectingAndThen(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2), Optional::get)));
        System.out.println(types2);

        Map<Dish.Type, Integer> totalCaloriesByType = menu.stream().collect(
                groupingBy(Dish::getType, TreeMap::new,
                        summingInt(dish -> dish.getCalories())));
        System.out.println(totalCaloriesByType);
    }

    @Test
    public void testPartion() {
        Map<Boolean, List<Dish>> map = menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println(map);

        Map<Boolean, Map<Dish.Type, List<Dish>>> map2 = menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        System.out.println(map2);

        Map<Boolean, Map<Boolean, List<Dish>>> map3 = menu.stream().collect(partitioningBy(Dish::isVegetarian, partitioningBy(d -> d.getCalories() > 500)));
        System.out.println(map3);

        Map<Boolean, Long> map4 = menu.stream().collect(partitioningBy(Dish::isVegetarian, counting()));
        System.out.println(map4);

        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = menu.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        collectingAndThen( reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2), Optional::get)));

        System.out.println(mostCaloricPartitionedByVegetarian);

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
                Stream.iterate(2, i -> i + 1).limit(1_000_000)
                             .collect(partitioningBy(n -> Stream.iterate(2, i -> i + 1)
                                                                .limit((long) Math.floor(Math.sqrt((double) n)) - 1)
                                                                .noneMatch(i -> n % i == 0)));
        //System.out.println(primes);
    }

    @Test
    public void testMemoizedPrimeNumbersPartition() {
        Map<Boolean, List<Integer>> primes =
                Stream.iterate(2, i -> i + 1).limit(1_000_000)
                             .collect(new MemoizedPrimeNumbersCollector());
        //System.out.println(primes);
    }

    @Test
    public void testMemoizedPrimeNumbersPartition2() {
        Map<Boolean, List<Integer>> primes =
                Stream.iterate(2, i -> i + 1).limit(100)
                             .collect(
                                     () -> new HashMap<Boolean, List<Integer>>() {{
                                         put(true, new ArrayList<Integer>());
                                         put(false, new ArrayList<Integer>());
                                     }},
                                     (acc, candidate) -> {
                                         acc.get( isPrime(acc.get(true), candidate) ).add(candidate);
                                     },
                                     (map1, map2) -> {
                                         map1.get(true).addAll(map2.get(true));
                                         map1.get(false).addAll(map2.get(false));
                                     });
        System.out.println(primes);
    }

    private static boolean isPrime(List<Integer> primes, Integer candidate) {
        return takeWhile(primes, i -> i <= Math.floor(Math.sqrt((double) candidate)))
                .stream()
                .noneMatch(i -> candidate % i == 0);
    }

    private static <A> List<A> takeWhile(final List<A> list, Predicate<A> p) {
        //IntStream.range(0, list.size()).filter(i -> !p.test(list.get(i))).findFirst().map(i -> list.subList(0, i)).orElse(list);
        int i = 0;
        for (A item : list) {
            if (!p.test(item)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }

    public static class MemoizedPrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

        @Override
        public Supplier<Map<Boolean, List<Integer>>> supplier() {
            return () -> new HashMap<Boolean, List<Integer>>() {{
                put(true, new ArrayList<Integer>());
                put(false, new ArrayList<Integer>());
            }};
        }

        @Override
        public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
            return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
                acc.get( isPrime(acc.get(true), candidate) ).add(candidate);
            };
        }

        private boolean isPrime(List<Integer> primes, Integer candidate) {
            double candidateRoot = Math.sqrt((double) candidate);
            return takeWhile(primes, i -> i <= candidateRoot)
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
                map1.get(true).addAll(map2.get(true));
                map1.get(false).addAll(map2.get(false));
                return map1;
            };
        }

        @Override
        public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
            return i -> i;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));
        }
    }

    public static class Dish {
        private final String name;
        private final boolean vegetarian;
        private final int calories;
        private final Type type;

        public Dish(String name, boolean vegetarian, int calories, Type type) {
            this.name = name;
            this.vegetarian = vegetarian;
            this.calories = calories;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public boolean isVegetarian() {
            return vegetarian;
        }

        public int getCalories() {
            return calories;
        }

        public Type getType() {
            return type;
        }

        public enum Type { MEAT, FISH, OTHER}

        @Override
        public String toString() {
            return name;
        }
    }

    public enum CaloricLevel { dietetic, normal, fat }
}
