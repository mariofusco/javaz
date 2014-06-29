package org.javaz;

import org.junit.*;

import static junit.framework.Assert.*;

public class PrimeFinderTest {

    @Test
    public void testLambda() {
        assertTrue(PrimeFinder.isPrimeFunctional(89));
        assertFalse(PrimeFinder.isPrimeFunctional(91));
    }

    @Test
    public void testImperative() {
        assertTrue(PrimeFinder.isPrimeImperative(89));
        assertFalse(PrimeFinder.isPrimeImperative(91));
    }
}
