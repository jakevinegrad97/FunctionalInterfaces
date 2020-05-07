package com.vinegrad.functionals;

import java.util.Objects;

@FunctionalInterface
public interface BiFunction<S, T, U> {

	U apply(S s, T t);
	
	default <V> BiFunction<S, T, V> andThen(Function<? super U, ? extends V> other) {
		Objects.requireNonNull(other);
		return (s, t) -> other.apply(apply(s, t));
	}
	
}
