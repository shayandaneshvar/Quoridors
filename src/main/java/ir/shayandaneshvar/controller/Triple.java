package ir.shayandaneshvar.controller;

import java.io.Serializable;
import java.util.Objects;

public class Triple<K, V, T> implements Serializable {
    private K first;
    private V second;
    private T third;

    public Triple(K first, V second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public K getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    public T getThird() {
        return third;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Triple<?, ?, ?> triple = (Triple<?, ?, ?>) obj;
        return Objects.equals(first, triple.first) &&
                Objects.equals(second, triple.second) &&
                Objects.equals(third, triple.third);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, third);
    }
}
