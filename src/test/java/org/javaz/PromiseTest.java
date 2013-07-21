package org.javaz;

import org.junit.Test;

import static org.javaz.Promise.promise;
import static org.junit.Assert.assertEquals;

public class PromiseTest {

    public int slowLength(String s) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return s.length();
    }

    public int slowDouble(int i) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return i*2;
    }

    @Test
    public void promiseTest() throws Exception {
        long start = System.currentTimeMillis();

        String s = "Hello";
        Promise<Integer> p = promise(() -> slowLength(s)).flatMap(i -> promise(() -> slowDouble(i)));

        System.out.println("promised in " + (System.currentTimeMillis() - start) + " msecs");
        assertEquals(10, (int) p.get());
        System.out.println("result found in " + (System.currentTimeMillis() - start) + " msecs");
    }
}
