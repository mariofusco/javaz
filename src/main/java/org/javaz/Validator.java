package org.javaz;

import java.util.*;
import java.util.function.*;

import static java.util.Arrays.asList;
import static org.javaz.Validation.*;

public class Validator<V> {
    private final Predicate<V> predicate;
    private final Object fail;

    public Validator(Predicate<V> predicate, Object fail) {
        this.predicate = predicate;
        this.fail = fail;
    }

    public Validation<Object, V> validate(V value) {
        return predicate.test(value) ? success(value) : failure(fail, value);
    }

    public static <V> Validation<List<Object>, V> validate(V value, Validator<V>... validators) {
        return asList(validators).stream()
                .reduce( success(value).failList(),
                        (Validation<List<Object>, V> validating, Validator<V> validator) -> validating.flatMap(validator::validate),
                        (Validation<List<Object>, V> v1, Validation<List<Object>, V> v2) -> null );
    }

    public static <V> Test<V> using(Predicate<V> predicate) {
        return new Test<V>(predicate);
    }

    public static class Test<V> {
        private final Predicate<V> predicate;

        public Test(Predicate<V> predicate) {
            this.predicate = predicate;
        }

        public Validator<V> withError(Object fail) {
            return new Validator<V>(predicate, fail);
        }
    }
}
