package com.seandurban.cache;

public interface Cache<T> {
    T retrieve(String key);
}
