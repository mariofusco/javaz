package org.javaz;

public class ElapsedTest {

    public static void main(String[] args) {
        StreamsTest test = new StreamsTest();
        for (int i = 0; i < 20; i++) {
            long start = System.nanoTime();
            //test.testPrimeNumbersPartition();
            test.testMemoizedPrimeNumbersPartition();
            long end = System.nanoTime();
            System.out.println("Done in " + ((end - start) / 1000 / 1000) + " msecs");
        }
    }
}
