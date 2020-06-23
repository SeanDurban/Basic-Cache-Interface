package com.seandurban;

public interface Cache<T> {
    T retrieve(String key);
}
