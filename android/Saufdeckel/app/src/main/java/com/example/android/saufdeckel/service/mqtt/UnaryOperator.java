package com.example.android.saufdeckel.service.mqtt;

/**
 * @author Andrei.Podznoev
 * Date    18.11.2017.
 */
public interface UnaryOperator<T> {
    void apply(T message);
}
