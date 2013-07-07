package org.javaz;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.util.Arrays.asList;
import static org.javaz.Validation.failure;
import static org.javaz.Validation.successList;

public class ValidationDSL {

    public static <V> Validation<? extends List<Object>, V> valid(V value, Function<? super V, Validation<?, ? extends V>>... validations) {
        return asList(validations).stream()
                .reduce( successList(value),
                         (Validation<? extends List<Object>, V> v, Function<? super V, Validation<?, ? extends V>> f) -> v.flatMap(f),
                         (Validation<? extends List<Object>, V> v1, Validation<? extends List<Object>, V> v2) -> {
                             if (v1.isSuccess()) return v2;
                             else if (v2.isSuccess()) return v1;
                             else return failure(v1.value, new ArrayList<Object>() {{
                                 addAll(v1.failure());
                                 addAll(v2.failure());
                             }});
                         } );

    }
}
