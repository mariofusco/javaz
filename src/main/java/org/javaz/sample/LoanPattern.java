package org.javaz.sample;

import java.util.function.*;

public class LoanPattern {

    public static class Resource {

        public Resource() {
            System.out.println("Opening resource");
        }

        public void operate() {
            System.out.println("Operating on resource");
            throw new RuntimeException("Oops!");
        }

        public void dispose() {
            System.out.println("Disposing resource");
        }

        public static void withResource(Consumer<Resource> consumer) {
            Resource res = new Resource();
            try {
                consumer.accept(res);
            } finally {
                res.dispose();
            }
        }
    }

    public static void main(String... args) {
        Resource.withResource(res -> res.operate());
    }
}
