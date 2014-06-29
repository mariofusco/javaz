package org.javaz;

import org.junit.*;

import java.util.stream.*;

public class ParallelStreamsTest {

    @Test
    public void testSum() {
        long total = Stream.iterate(1L, i -> i + 1).limit(100_000_000).reduce(Long::sum).get();
        //long total = LongStream.rangeClosed(1, 100_000_000).reduce(Long::sum).getAsLong();
        System.out.println(total);
    }

    @Test
    public void testParallelSum() {
        long total = Stream.iterate(1L, i -> i + 1).limit(100_000_000).parallel().reduce(Long::sum).get();
        //long total = LongStream.rangeClosed(1, 100_000_000).parallel().reduce(Long::sum).getAsLong();
        System.out.println(total);
    }
}
