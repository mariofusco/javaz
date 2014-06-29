package org.javaz;

public class ElapsedTest {

    public static void main(String[] args) {
        long best = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();

            //new ParallelStreamsTest().testSum();
            //new ParallelStreamsTest().testParallelSum();

            //new StreamsTest().testPrimeNumbersPartition();
            new StreamsTest().testMemoizedPrimeNumbersPartition();

            long elapsed = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Done in " + elapsed + " msecs");
            if (elapsed < best) best = elapsed;
        }
        System.out.println("Best execution done in " + best + " msecs");
    }
}
