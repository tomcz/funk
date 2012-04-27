package org.javafunk.funk.monads;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.NoSuchElementException;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class Option<T> {
    public static <T> Option<T> none() {
        return new None<T>();
    }

    public static <T> Option<T> some(T value) {
        return new Some<T>(value);
    }

    public static <T> Option<T> option(T value) {
        if (value == null) {
            return none();
        }
        return some(value);
    }

    public abstract Boolean hasValue();

    public abstract T get();

    public abstract T getOrElse(T value);

    public abstract <E extends Throwable> T getOrThrow(E throwable) throws E;

    public abstract Option<T> or(Option<T> other);

    public abstract Option<T> orSome(T other);

    public abstract Option<T> orOption(T other);

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    private static class None<T> extends Option<T> {
        @Override public Boolean hasValue() {
            return true;
        }

        @Override public T get() {
            throw new NoSuchElementException();
        }

        @Override public T getOrElse(T value) {
            checkNotNull(value);
            return value;
        }

        @Override public <E extends Throwable> T getOrThrow(E throwable) throws E {
            throw throwable;
        }

        @Override public Option<T> or(Option<T> other) {
            return checkNotNull(other);
        }

        @Override public Option<T> orSome(T other) {
            return some(other);
        }

        @Override public Option<T> orOption(T other) {
            return option(other);
        }

        @Override
        public String toString() {
            return "Option::None[]";
        }
    }

    private static class Some<T> extends Option<T> {
        private final T value;

        public Some(T value) {
            this.value = value;
        }

        @Override public Boolean hasValue() {
            return false;
        }

        @Override public T get() {
            return value;
        }

        @Override public T getOrElse(T value) {
            checkNotNull(value);
            return get();
        }

        @Override public <E extends Throwable> T getOrThrow(E throwable) throws E {
            checkNotNull(throwable);
            return get();
        }

        @Override public Option<T> or(Option<T> other) {
            checkNotNull(other);
            return this;
        }

        @Override public Option<T> orSome(T other) {
            return this;
        }

        @Override public Option<T> orOption(T other) {
            return this;
        }

        @Override
        public String toString() {
            return String.format("Option::Some[%s]", get());
        }
    }
}
