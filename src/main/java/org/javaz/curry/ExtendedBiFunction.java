package org.javaz.curry;

import java.util.function.*;

@FunctionalInterface
public interface ExtendedBiFunction<T, U, R> extends BiFunction<T, U, R> {

    default Function<U, R> curry1(T t) {
        return u -> apply(t, u);
    }

    default Function<T, R> curry2(U u) {
        return t -> apply(t, u);
    }

    default <V> ExtendedBiFunction<V, U, R> compose1(Function<? super V, ? extends T> before) {
        return (v, u) -> apply(before.apply(v), u);
    }

    default <V> ExtendedBiFunction<T, V, R> compose2(Function<? super V, ? extends U> before) {
        return (t, v) -> apply(t, before.apply(v));
    }
}
