package org.javaz;

import java.util.*;
import java.util.function.*;

import static java.util.Optional.*;

public abstract class Try<T> {

    private Try() { }

    public static <T> Try<T> success(T value) {
        return new Success<T>(value);
    }

    public static <T> Try<T> attempt(ThrowingSupplier<? extends T> supplier) {
        try {
            return new Success<T>(supplier.get());
        } catch(Exception e) {
            return new Failure<T>(e);
        }
    }

    @FunctionalInterface
    public interface ThrowingSupplier<T> {
        T get() throws Exception;
    }

    /**
     * Returns true if the Try is a Success, false otherwise.
     */
    public abstract boolean isSuccess();

    /**
     * Returns the value from this Success or throws the exception (wrapped in a runtime one) if this is a Failure.
     */
    public abstract T get();

    /**
     * Converts this to a Failure if the predicate is not satisfied.
     */
    public abstract Try<T> filter(Predicate<? super T> p);

    /**
     * Maps the given function to the value from this Success or returns this if this is a Failure.
     */
    public abstract <U> Try<U> map(Function<? super T, ? extends U> f);

    /**
     * Returns the given function applied to the value from this Success or returns this if this is a Failure.
     */
    public abstract <U> Try<U> flatMap(Function<? super T, Try<U>> f);

    /**
     * Applies the given function f if this is a Success, otherwise does nothing if this is a Failure.
     */
    public abstract void apply(Consumer<? super T> c);

    /**
     * Applies the given function f if this is a Failure, otherwise returns this if this is a Success.
     * This is like map for the exception.
     */
    public abstract <U extends T> Try<U> recover(Function<Exception, ? extends U> f);

    /**
     * Applies the given function f if this is a Failure, otherwise returns this if this is a Success.
     */
    public abstract <U extends T> Try<U> recoverWith(Function<Exception, Try<U>> f);

    /**
     * Returns the value from this Success or the given default argument if this is a Failure
     */
    public abstract T orElse(T other);

    /**
     * Return the value from this Success otherwise invoke {@code other} and return
     * the result of that invocation.
     */
    public abstract T orElseGet(Supplier<? extends T> other);

    /**
     * Returns Optional.EMPTY if this is a Failure or an Optional containing the value if this is a Success.
     */
    public abstract Optional<T> toOptional();

    /**
     * Completes this Try by applying the function f to this if this is of type Failure,
     * or conversely, by applying s if this is a Success.
     */
    public abstract <U> Try<U> transform(Function<? super T, Try<U>> s, Function<Exception, Try<U>> f);

    public static class Success<T> extends Try<T> {
        private final T value;

        public Success(T value) {
            this.value = value;
        }

        @Override
        public boolean isSuccess() {
            return true;
        }

        @Override
        public T get() {
            return value;
        }

        @Override
        public Try<T> filter(Predicate<? super T> p) {
            return p.test(value) ? this : new Failure<T>(null);
        }

        @Override
        public <U> Try<U> map(Function<? super T, ? extends U> f) {
            try {
                return new Success<U>(f.apply(value));
            } catch (Exception e) {
                return new Failure<U>(e);
            }
        }

        @Override
        public <U> Try<U> flatMap(Function<? super T, Try<U>> f) {
            try {
                return f.apply(value);
            } catch (Exception e) {
                return new Failure<U>(e);
            }
        }

        @Override
        public void apply(Consumer<? super T> c) {
            c.accept(value);
        }

        @Override
        public <U extends T> Try<U> recover(Function<Exception, ? extends U> f) {
            return (Try<U>)this;
        }

        @Override
        public <U extends T> Try<U> recoverWith(Function<Exception, Try<U>> f) {
            return (Try<U>)this;
        }

        @Override
        public T orElse(T other) {
            return value;
        }

        @Override
        public T orElseGet(Supplier<? extends T> other) {
            return value;
        }

        @Override
        public Optional<T> toOptional() {
            return of(value);
        }

        @Override
        public <U> Try<U> transform(Function<? super T, Try<U>> s, Function<Exception, Try<U>> f) {
            return flatMap(s);
        }

        @Override
        public String toString() {
            return "Success(" + value + ")";
        }
    }

    public static class Failure<T> extends Try<T> {
        private final Exception e;

        public Failure(Exception e) {
            this.e = e;
        }

        @Override
        public boolean isSuccess() {
            return false;
        }

        @Override
        public T get() {
            throw e instanceof RuntimeException ? (RuntimeException)e : new RuntimeException(e);
        }

        @Override
        public Try<T> filter(Predicate<? super T> p) {
            return this;
        }

        @Override
        public <U> Try<U> map(Function<? super T, ? extends U> f) {
            return (Try<U>)this;
        }

        @Override
        public <U> Try<U> flatMap(Function<? super T, Try<U>> f) {
            return (Try<U>)this;
        }

        @Override
        public void apply(Consumer<? super T> c) { }

        @Override
        public <U extends T> Try<U> recover(Function<Exception, ? extends U> f) {
            return new Success<>(f.apply(e));
        }

        @Override
        public <U extends T> Try<U> recoverWith(Function<Exception, Try<U>> f) {
            return f.apply(e);
        }

        @Override
        public T orElse(T other) {
            return other;
        }

        @Override
        public T orElseGet(Supplier<? extends T> other) {
            return other.get();
        }

        @Override
        public Optional<T> toOptional() {
            return empty();
        }

        @Override
        public <U> Try<U> transform(Function<? super T, Try<U>> s, Function<Exception, Try<U>> f) {
            return f.apply(e);
        }

        @Override
        public String toString() {
            return "Failure(" + e + ")";
        }
    }
}
