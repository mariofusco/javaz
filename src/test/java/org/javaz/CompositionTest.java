package org.javaz;

import org.junit.*;

import java.util.function.*;

public class CompositionTest {
    public static class Bird { }

    public static class Cat {
        public CatWithCatch capture(Bird bird) {
            return new CatWithCatch(bird);
        }
    }

    public static class CatWithCatch {
        private final Bird bird;
        public CatWithCatch(Bird bird) {
            this.bird = bird;
        }

        public FullCat eat() {
            return new FullCat();
        }
    }

    public static class FullCat { }

    @Test
    public void test() {
        BiFunction<Cat, Bird, FullCat> story = ((BiFunction<Cat, Bird, CatWithCatch>)Cat::capture).compose(CatWithCatch::eat);
        FullCat fullCat = story.apply(new Cat(), new Bird());
    }

}
