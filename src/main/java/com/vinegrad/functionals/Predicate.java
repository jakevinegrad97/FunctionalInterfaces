package com.vinegrad.functionals;

import java.util.Objects;

@FunctionalInterface
public interface Predicate<T> {

	boolean test(T t);
	
	default Predicate<T> or(Predicate<? super T> other) {
		Objects.requireNonNull(other);
		return t -> test(t) || other.test(t);
	}
	
	default Predicate<T> and(Predicate<? super T> other) {
		Objects.requireNonNull(other);
		return t -> test(t) && other.test(t);
	}
	
	static <T> Predicate<T> not(Predicate<T> p) {
		Objects.requireNonNull(p);
		return t -> !p.test(t);
	}
	
	default Predicate<T> negate() {
		return t -> !test(t);
	}
}
