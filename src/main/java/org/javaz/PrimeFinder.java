/***
 * Excerpted from "Functional Programming in Java",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/vsjava8 for more book information.
 ***/
package org.javaz;

import static java.util.stream.IntStream.range;

public class PrimeFinder {

    public static boolean isPrimeFunctional(final int number) {
        return number > 1 &&
                range(2, (int) Math.sqrt(number) + 1)
                        .noneMatch(divisor -> number % divisor == 0);
    }

    public static boolean isPrimeImperative(final int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i < (int) Math.sqrt(number) + 1; i++) {
            if ( number % i == 0 ) {
                return false;
            }
        }
        return true;
    }
}
