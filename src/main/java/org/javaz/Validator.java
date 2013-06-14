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
        return predicate.test(value) ? success(value) : failure(value, fail);
    }

    public static <V> Validation<List<Object>, V> validate(V value, Validator<V>... validators) {
        return asList(validators).stream()
                .reduce( successList(value),
                        (Validation<List<Object>, V> validating, Validator<V> validator) -> validating.flatMap(validator::validate),
                        (Validation<List<Object>, V> v1, Validation<List<Object>, V> v2) -> null );
    }
}
