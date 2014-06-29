package org.javaz.trampoline;

import static org.javaz.trampoline.TailCalls.*;

public class Adder {

    public static long sum(long number) {
        if(number == 1)
            return number;
        else
            return number + sum(number - 1);
    }

    public static long trampolineSum(long number) {
        return sum(1, number).invoke();
    }

    public static TailCall<Long> sum(long accumulator, long number) {
        if(number == 1)
            return done(accumulator);
        else
            return call(() -> sum(accumulator + number, number - 1));
    }
}
