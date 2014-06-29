package org.javaz.curry;

import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        Converter converter = new Converter();
        double tenMilesInKm = converter.apply(1.609, 10.0);
        System.out.println(converter.apply(1.609, 10.0));
        System.out.println(converter.apply(1.609, 20.0));
        System.out.println(converter.apply(1.609, 50.0));

        Function<Double, Double> mi2kmConverter = converter.curry1(1.609);
        System.out.println(mi2kmConverter.apply(10.0));
        System.out.println(mi2kmConverter.apply(20.0));
        System.out.println(mi2kmConverter.apply(50.0));

        Function<Double, Double> ounce2gramConverter = converter.curry1(28.345);
        System.out.println(ounce2gramConverter.apply(10.0));
        System.out.println(ounce2gramConverter.apply(20.0));
        System.out.println(ounce2gramConverter.apply(50.0));

        Function<Double, Double> celsius2farenheitConverter = converter.curry1(9.0/5).andThen(n -> n + 32);
        System.out.println(celsius2farenheitConverter.apply(10.0));
        System.out.println(celsius2farenheitConverter.apply(20.0));
        System.out.println(celsius2farenheitConverter.apply(50.0));

        Function<Double, Double> farenheit2celsiusConverter = converter.compose2((Double n) -> n - 32).curry1(5.0/9);
        System.out.println(farenheit2celsiusConverter.apply(10.0));
        System.out.println(farenheit2celsiusConverter.apply(20.0));
        System.out.println(farenheit2celsiusConverter.apply(50.0));
    }
}
