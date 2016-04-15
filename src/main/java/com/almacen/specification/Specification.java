package com.almacen.specification;

public interface Specification<T> {
    Boolean isSatisfiedBy(T candidate);

    Specification<T> and(Specification<T> other);
    Specification<T> or(Specification<T> other);
}
