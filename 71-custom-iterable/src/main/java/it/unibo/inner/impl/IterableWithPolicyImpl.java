package it.unibo.inner.impl;

import java.util.Iterator;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    private T[] array;


    public IterableWithPolicyImpl(T[] array) {
        this.array = array;
    }

    private class PolicyIterator implements Iterator<T>{

        private int position = 0;

        @Override
        public boolean hasNext() {
            return position < array.length;
        }

        @Override
        public T next() {
            return array[position++];
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new PolicyIterator();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIterationPolicy'");
    }

}