package org.javaz;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
import java.util.function.Supplier;

public class Promise<A> implements Future<A> {

    private final CompletableFuture<A> future;

    private Promise(CompletableFuture<A> future) {
        this.future = future;
    }

    public static final <A> Promise<A> promise(Supplier<A> supplier) {
        return new Promise<A>(CompletableFuture.supplyAsync(supplier));
    }

    // Monadic methods

    public <B> Promise<B> map(Function<? super A,? extends B> f) {
        return new Promise<B>(future.thenApplyAsync(f));
    }

    public <B> Promise<B> flatMap(Function<? super A, Promise<B>> f) {
        return new Promise<B>(future.thenComposeAsync(a -> f.apply(a).future));
    }

    // Delegated methods

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return future.cancel(mayInterruptIfRunning);
    }

    @Override
    public boolean isCancelled() {
        return future.isCancelled();
    }

    @Override
    public boolean isDone() {
        return future.isDone();
    }

    @Override
    public A get() throws InterruptedException, ExecutionException {
        return future.get();
    }

    @Override
    public A get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return future.get(timeout, unit);
    }
}
