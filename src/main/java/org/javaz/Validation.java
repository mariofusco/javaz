package org.javaz;

import java.util.*;
import java.util.function.Function;

public abstract class Validation<L, A> {

    final A value;

    private Validation(A value) {
        this.value = value;
    }

    public abstract <B> Validation<L, B> map(Function<? super A, ? extends B> mapper);

    public abstract <B> Validation<L, B> flatMap(Function<? super A, Validation<?, ? extends B>> mapper);

    public abstract boolean isSuccess();

    public A getValue() {
        return value;
    }

    public static <L, A> Success<L, A> success(A value) {
        return new Success<L, A>(value);
    }

    public static <L, A> SuccessList<L, A> successList(A value) {
        return new SuccessList<L, A>(value);
    }

    public static <L, A> Failure<L, A> failure(A value, L left) {
        return new Failure(value, left);
    }

    public static final class Success<L, A> extends Validation<L, A> {

        private Success(A value) {
            super(value);
        }

        @Override
        public <B> Validation<L, B> map(Function<? super A, ? extends B> mapper) {
            return success(mapper.apply(value));
        }

        @Override
        public <B> Validation<L, B> flatMap(Function<? super A, Validation<?, ? extends B>> mapper) {
            return (Validation<L, B>) mapper.apply(value);
        }

        @Override
        public boolean isSuccess() {
            return true;
        }

        @Override
        public String toString() {
            return "Success( " + value + " )";
        }
    }

    public static final class SuccessList<L, A> extends Validation<List<L>, A> {

        public SuccessList(A value) {
            super(value);
        }

        @Override
        public <B> Validation<List<L>, B> map(Function<? super A, ? extends B> mapper) {
            return successList(mapper.apply(value));
        }

        @Override
        public <B> Validation<List<L>, B> flatMap(Function<? super A, Validation<?, ? extends B>> mapper) {
            Validation<?, ? extends B> result = mapper.apply(value);
            return (Validation<List<L>, B>)(result.isSuccess() ?
                    successList(result.value) :
                    new FailureList<L, B>(result.value, ((Failure<L, B>)result).left));
        }

        @Override
        public boolean isSuccess() {
            return true;
        }

        @Override
        public String toString() {
            return "Success( " + value + " )";
        }
    }

    public static final class Failure<L, A> extends Validation<L, A> {

        private final L left;

        public Failure(A value, L left) {
            super(value);
            this.left = left;
        }

        @Override
        public <B> Validation<L, B> map(Function<? super A, ? extends B> mapper) {
            return new Failure(mapper.apply(value), left);
        }

        @Override
        public <B> Validation<L, B> flatMap(Function<? super A, Validation<?, ? extends B>> mapper) {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public boolean isSuccess() {
            return false;
        }

        @Override
        public String toString() {
            return "Failure( " + left + " )";
        }
    }

    public static final class FailureList<L, A> extends Validation<List<L>, A> {

        private final List<L> left;

        public FailureList(A value, L left) {
            super(value);
            this.left = new ArrayList<L>() {{
                add(left);
            }};
        }

        private FailureList(A value, List<L> left) {
            super(value);
            this.left = left;
        }

        @Override
        public <B> Validation<List<L>, B> map(Function<? super A, ? extends B> mapper) {
            return new FailureList(mapper.apply(value), left);
        }

        @Override
        public <B> Validation<List<L>, B> flatMap(Function<? super A, Validation<?, ? extends B>> mapper) {
            Validation<?, ? extends B> result = mapper.apply(value);
            return (Validation<List<L>, B>)(result.isSuccess() ?
                    new FailureList(result.value, left) :
                    new FailureList<L, B>(result.value, new ArrayList<L>(left) {{
                        add(((Failure<L, B>)result).left);
                    }}));
        }

        @Override
        public boolean isSuccess() {
            return false;
        }

        @Override
        public String toString() {
            return "Failure( " + left + " )";
        }
    }
}
