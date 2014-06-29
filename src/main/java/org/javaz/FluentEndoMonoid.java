package org.javaz;

public class FluentEndoMonoid<A> implements EndoMonoid<A> {
    private final Endomorphism<A> endo;


    public FluentEndoMonoid(Endomorphism<A> endo) {
        this.endo = endo;
    }

    public FluentEndoMonoid(Endomorphism<A> endo, boolean b) {
        this.endo = b ? endo : zero();
    }

    public FluentEndoMonoid<A> add(Endomorphism<A> other) {
        return new FluentEndoMonoid<A>(apply(endo, other));
    }

    public FluentEndoMonoid<A> add(Endomorphism<A> other, boolean b) {
        return add(b ? other : zero());
    }

    public Endomorphism<A> get() {
        return endo;
    }

    public static <A> FluentEndoMonoid<A> endo(Endomorphism<A> f) {
        return new FluentEndoMonoid<A>(f);
    }

    public static <A> FluentEndoMonoid<A> endo(Endomorphism<A> f, boolean b) {
        return new FluentEndoMonoid<A>(f, b);
    }
}
