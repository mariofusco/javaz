package org.javaz;

import org.junit.*;

import static junit.framework.Assert.assertEquals;
import static org.javaz.Try.attempt;

public class TryTest {

    public String first(boolean throwEx) throws Exception {
        if (throwEx) throw new RuntimeException("Cannot read first");
        return "6";
    }

    public String second(boolean throwEx) throws Exception {
        if (throwEx) throw new RuntimeException("Cannot read second");
        return "2";
    }

    @Test
    public void test() {
        Try<Integer> result = attempt(() -> first(false))
                .map(Integer::parseInt)
                .flatMap(first -> attempt(() -> second(false)).map(Integer::parseInt).map(second -> first / second));
        assertEquals(3, (int)result.orElse(-1));
    }
}
