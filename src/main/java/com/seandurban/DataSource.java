package com.seandurban;

public interface DataSource<T> {
    T retrieve(String key);
}
