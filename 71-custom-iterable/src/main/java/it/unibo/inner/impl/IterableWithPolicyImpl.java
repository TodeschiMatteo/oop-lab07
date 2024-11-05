package it.unibo.inner.impl;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    private final List<T> array;
    private Predicate<T> policy;

    public IterableWithPolicyImpl(final T[] input) {
        this(
            input,
            new Predicate<>() {
                @Override
                public boolean test(T elem) {
                    return true;
                }
            }
        );
    }

    public IterableWithPolicyImpl(T[] input, Predicate<T> policy) {
        this.array = List.of(requireNonNull(input, "The array must not be null"));
        this.policy = requireNonNull(policy, "The predicate must not be null");
    }

    private class PolicyIterator implements Iterator<T>{

        private int position = 0;

        public boolean hasNext() {
            while (position < array.size()) {
                if (policy.test(array.get(position++))) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public T next() {
            if (hasNext()) {
                return array.get(position++);
            }
            throw new NoSuchElementException("No other elements are avaible");
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new PolicyIterator();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.policy = filter;
    }

}