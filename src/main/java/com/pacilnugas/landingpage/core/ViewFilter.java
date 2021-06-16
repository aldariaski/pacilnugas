package com.pacilnugas.landingpage.core;

public abstract class ViewFilter<T> implements FilterStrategy {
    private T value;

    protected ViewFilter(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
