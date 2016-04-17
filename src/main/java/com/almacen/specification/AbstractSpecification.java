package com.almacen.specification;


import com.almacen.specification.logical.AndSpecification;
import com.almacen.specification.logical.OrSpecification;

public abstract class AbstractSpecification<T> implements Specification<T> {

    @Override
    public final Specification<T> and(Specification<T> other) {

        return new AndSpecification(this, other);

    }

    @Override
    public final Specification<T> or(Specification<T> other) {

        return new OrSpecification(this, other);

    }
}
