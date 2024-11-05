package it.unibo.inner.impl;

import java.util.Iterator;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    private T[] array;
    private Predicate<T> policy;


    public IterableWithPolicyImpl(T[] array) {
        this.array = array;
        policy = new Predicate<>() {
            @Override
            public boolean test(T elem) {
                return true;
            }
        };
    }

    public IterableWithPolicyImpl(T[] array, Predicate<T> predicate) {
        this.array = array;
        this.policy = predicate;
    }

    private class PolicyIterator implements Iterator<T>{

        private int position = 0;
        private T next = null;

        @Override
        public boolean hasNext() {
            while(this.position < array.length){
                if (policy.test(array[this.position])){
                    next = array[this.position];
                    this.position++;
                    return true;
                }
                this.position++;
            }
            return false;
        }

        @Override
        public T next() {
            return this.next;
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