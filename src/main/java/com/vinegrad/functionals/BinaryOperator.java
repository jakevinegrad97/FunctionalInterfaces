package com.vinegrad.functionals;

import java.util.Objects;

@FunctionalInterface
public interface BinaryOperator<T> {

	T apply(T t1, T t2);
	
	default BinaryOperator<T> andThen(UnaryOperator<T> after) {
		Objects.requireNonNull(after);
		return (t1, t2) -> after.apply(apply(t1, t2));
	}
	
	static <T> BinaryOperator<T> maxBy(Comparator<? super T> comparator) {
		Objects.requireNonNull(comparator);
		return (t1, t2) -> comparator.compare(t1, t2) >= 0 ? t1 : t2;
	}
	
	static <T> BinaryOperator<T> minBy(Comparator<? super T> comparator) {
		Objects.requireNonNull(comparator);
		return (t1, t2) -> comparator.compare(t1, t2) <= 0 ? t1 : t2;
	}
}
