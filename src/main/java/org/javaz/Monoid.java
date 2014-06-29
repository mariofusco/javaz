package org.javaz;

import java.util.function.*;

public interface Monoid<A> extends BinaryOperator<A> {

    A zero();
}
