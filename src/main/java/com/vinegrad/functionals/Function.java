package com.vinegrad.functionals;

import java.util.Objects;

@FunctionalInterface
public interface Function<S, T> {

	T apply(S s);
	
	default <U> Function<S, U> andThen(Function<? super T, ? extends U> other) {
		Objects.requireNonNull(other);
		return s -> other.apply(apply(s));
	}
	
	default <R> Function<R, T> compose(Function<? super R, ? extends S> other) {
		Objects.requireNonNull(other);
		return r -> apply(other.apply(r));
	}
	
	static <T> Function<T, T> identity() {
		return t -> t;
	}
}