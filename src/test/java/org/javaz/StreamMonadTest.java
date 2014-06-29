package org.javaz;

import org.junit.*;

import java.util.*;
import java.util.stream.*;

import static java.lang.Math.sqrt;
import static java.util.stream.IntStream.range;

public class StreamMonadTest {

    int[] a = new int[] {1, 2};

    @Test
    public void test() {
        int n = 10;
        Stream<int[]> res = range(1, n).boxed().flatMap(i -> range(1, i).mapToObj(j -> new int[]{i, j})).filter(a -> isPrime(a[0] + a[1]));
        Iterator<int[]> i = res.iterator();
        while (i.hasNext()) {
            System.out.println(Arrays.toString(i.next()));
        }
    }

    public boolean isPrime(int n) {
        return n > 1 && range(2, (int) Math.sqrt(n) + 1).noneMatch(divisor -> n % divisor == 0);
    }
}
