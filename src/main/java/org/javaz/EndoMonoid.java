package org.javaz;

import java.util.function.*;

public interface EndoMonoid<A> extends Monoid<Endomorphism<A>> {

    @Override
    default Endomorphism<A> apply(Endomorphism<A> a1, Endomorphism<A> a2) {
        return (A a) -> a2.apply(a1.apply(a));
    }

    @Override
    default Endomorphism<A> zero() {
        return a -> a;
    }
}
