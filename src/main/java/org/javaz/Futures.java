package org.javaz;

import java.util.concurrent.*;
import java.util.function.*;

public class Futures {

    public ExecutorService executorService = Executors.newCachedThreadPool();

    public <T> Future<T> future(Callable<T> callable) {
        return executorService.submit(callable);
    }

    public void test() {
        int i = 1;
        int j = 2;

        Future<Integer> result = future( () -> i + j );
    }

    public static <A,B> Future<B> map(Future<A> future, Function<A,B> f) {
        return new Future<B>() {
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
            public B get() throws InterruptedException, ExecutionException {
                return f.apply(future.get());
            }

            @Override
            public B get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return f.apply(future.get(timeout, unit));
            }
        };
    }

    public Work bob(Work w) { return null; }
    public Work alice(Work w) { return null; }
/*
    public Work bobThenAlice(Work w) {
        Work b = bob(w);
        return alice(b);
    }
*/
    Function<Work, Callable<Work>> bob = new Function<Work, Callable<Work>>() {
        public Callable<Work> apply(final Work w) {
            return new Callable<Work>() {
                public Work call() { return null; }
            };
        }
    };

    Function<Work, Callable<Work>> alice = new Function<Work, Callable<Work>>() {
        public Callable<Work> apply(final Work w) {
            return new Callable<Work>() {
                public Work call() { return null; }
            };
        }
    };

    public Callable<Work> bobThenAlice(Work w) {
        return bind(bob.apply(w), alice);
    }

    public static class Work { }

    public static <A> Callable<A> callable(final A a) {
        return new Callable<A>() {
            public A call() {
                return a;
            }
        };
    }

    public static <A, B> Callable<B> bind(final Callable<A> a, final Function<A, Callable<B>> f) {
        return new Callable<B>() {
            public B call() throws Exception {
                return f.apply(a.call()).call();
            }
        };
    }
/*
    public static <A> Future<A> future(Callable<A> c) {
        return Executors.newSingleThreadExecutor().submit(c);
    }
*/
}
