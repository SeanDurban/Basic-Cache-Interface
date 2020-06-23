package com.seandurban.cache;

import com.seandurban.datasource.DataSource;

import java.util.HashMap;

public class LimitedMemoryCache implements Cache {
    HashMap<String, Object> cache;

    DataSource dataSource;

    int maxSize;

    LimitedMemoryCache(DataSource dataSource, int maxSize) {
        this.cache = new HashMap<>();
        this.dataSource = dataSource;
        this.maxSize = maxSize;
    }

    public Object retrieve(String key) {
        if(cache.containsKey(key)) {
            return cache.get(key);
        }
        else {
            Object dataEntry = dataSource.retrieve(key);
            addToCache(key, dataEntry);
            return dataEntry;
        }
    }

    void addToCache(String key, Object dataEntry) {
        if(cache.size() >= maxSize) {
            evict();
        }
        cache.put(key, dataEntry);
    }

    void evict() {
        if(cache.size() > 0) {
            String keyToEvict = (String) cache.keySet().toArray()[0];
            cache.remove(keyToEvict);
        }
    }
}

