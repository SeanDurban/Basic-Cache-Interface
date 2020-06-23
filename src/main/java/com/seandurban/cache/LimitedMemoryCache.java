package com.seandurban.cache;

import com.seandurban.datasource.DataSource;

import java.util.HashMap;

public class LimitedMemoryCache<T> implements Cache {
    HashMap<String, T> cache;

    DataSource<T> dataSource;

    int maxSize;

    LimitedMemoryCache(DataSource dataSource, int maxSize) {
        this.cache = new HashMap<>();
        this.dataSource = dataSource;
        this.maxSize = maxSize;
    }

    public T retrieve(String key) {
        if(cache.containsKey(key)) {
            return cache.get(key);
        }
        else {
            T dataEntry = dataSource.retrieve(key);
            addToCache(key, dataEntry);
            return dataEntry;
        }
    }

    void addToCache(String key, T dataEntry) {
        if(cache.size() >= maxSize) {
            evict();
        }
        cache.put(key, dataEntry);
    }

    //Randomly evicts
    void evict() {
        if(cache.size() > 0) {
            String keyToEvict = (String) cache.keySet().toArray()[0];
            cache.remove(keyToEvict);
        }
    }
}

