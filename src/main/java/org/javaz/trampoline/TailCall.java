package org.javaz.trampoline;

import java.util.stream.*;

@FunctionalInterface
public interface TailCall<T> {

    TailCall<T> apply();

    default boolean isComplete() { return false; }

    default T result() { throw new UnsupportedOperationException(); }

    default T invoke() {
        return Stream.iterate(this, TailCall::apply)
                .filter(TailCall::isComplete)
                .findFirst()
                .get()
                .result();
    }
}