package com.almacen.specification.logical;

import com.almacen.specification.AbstractSpecification;
import com.almacen.specification.Specification;

public class AndSpecification<T> extends AbstractSpecification<T> {

    private Specification<T> first;
    private Specification<T> second;

    public AndSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Boolean isSatisfiedBy(T candidate) {
        return first.isSatisfiedBy(candidate) && second.isSatisfiedBy(candidate);
    }
}
