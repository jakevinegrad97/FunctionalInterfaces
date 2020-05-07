package com.vinegrad.functionals;

import java.util.Objects;

@FunctionalInterface
public interface UnaryOperator<T> {

	T apply(T t);
	
	default UnaryOperator<T> andThen(UnaryOperator<T> other) {
		Objects.requireNonNull(other);
		return t -> other.apply(apply(t));
	}
	
	default UnaryOperator<T> compose(UnaryOperator<T> other) {
		Objects.requireNonNull(other);
		return t -> apply(other.apply(t));
	}
	
	static <T> UnaryOperator<T> identity() {
		return t -> t;
	}
}
