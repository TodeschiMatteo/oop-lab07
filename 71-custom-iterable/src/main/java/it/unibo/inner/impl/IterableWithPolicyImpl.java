package it.unibo.inner.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    private List<T> array;
    private Predicate<T> policy;

    public IterableWithPolicyImpl(T[] input) {
        this.array = new ArrayList<>(List.of(input));
        policy = new Predicate<>() {
            @Override
            public boolean test(T elem) {
                return true;
            }
        };
    }

    public IterableWithPolicyImpl(T[] input, Predicate<T> policy) {
        this.array = new ArrayList<>(List.of(input));
        this.policy = policy;
    }

    private class PolicyIterator implements Iterator<T>{

        private int position = 0;

        public boolean hasNext() {
            while (position < array.size()) {
                if (policy.test(array.get(position))) {
                    return true;
                }
                position++;
            }
            return false;
        }

        @Override
        public T next() {
            if (hasNext()) {
                return array.get(position++);
            }
            return null;
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