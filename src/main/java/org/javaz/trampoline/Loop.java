package org.javaz.trampoline;

import java.util.function.*;

import static org.javaz.trampoline.TailCalls.*;

public class Loop {

    public static void loop(int limit, Consumer<Integer> consumer) {
        loop(0, limit, consumer);
    }

    public static void loop(int counter, int limit, Consumer<Integer> consumer) {
        if (counter < limit) {
            consumer.accept(counter);
            loop(counter + 1, limit, consumer);
        }
    }

    public static void trampolineLoop(int limit, Consumer<Integer> consumer) {
        trampolineLoop(0, limit, consumer).invoke();
    }

    public static TailCall<Consumer<Integer>> trampolineLoop(int counter, int limit, Consumer<Integer> consumer) {
        if (counter >= limit)
            return done(consumer);
        else
            return call(() -> {
                consumer.accept(counter);
                return trampolineLoop(counter + 1, limit, consumer);
            });
    }
}
