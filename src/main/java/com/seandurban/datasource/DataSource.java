package com.seandurban.datasource;

public interface DataSource<T> {
    T retrieve(String key);
}
