package com.vinegrad.functionals;

import java.util.Objects;

@FunctionalInterface
public interface Comparator<T> {

	int compare(T t1, T t2);
	
	default Comparator<T> thenComparing(Comparator<? super T> other) {
		Objects.requireNonNull(other);
		return (t1, t2) -> compare(t1, t2) != 0 ? compare(t1, t2) : other.compare(t1, t2);
	}
	
	default <U extends Comparable<? super U>> Comparator<T> thenComparing(Function<? super T, ? extends U> keyExtractor) {
		Objects.requireNonNull(keyExtractor);
		return thenComparing(comparing(keyExtractor));
	}
	
	default Comparator<T> reversed() {
		return (t1, t2) -> compare(t2, t1);
	}
	
	static <T, U extends Comparable<? super U>> Comparator<T> comparing(Function<? super T, ? extends U> keyExtractor) {
		Objects.requireNonNull(keyExtractor);
		return (t1, t2) -> keyExtractor.apply(t1).compareTo(keyExtractor.apply(t2));
	}
	
}
