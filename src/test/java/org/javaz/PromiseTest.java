package org.javaz;

import org.junit.Test;

import static org.javaz.Promise.promise;
import static org.junit.Assert.assertEquals;

public class PromiseTest {

    private void someLongComputation() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int slowLength(String s) {
        someLongComputation();
        return s.length();
    }

    public int slowDouble(int i) {
        someLongComputation();
        return i*2;
    }

    @Test
    public void promiseTest() throws Exception {
        long start = System.currentTimeMillis();

        String s = "Hello";
        Promise<Integer> p =
                promise(() -> slowLength(s))
                .flatMap(i -> promise(() -> slowDouble(i)));

        System.out.println("Promised in " + (System.currentTimeMillis() - start) + " msecs");

        int result = p.get();
        System.out.println("Result= " + result + "; found in " + (System.currentTimeMillis() - start) + " msecs");
        assertEquals(10, result);
    }
}
