package com.seandurban.cache;

import com.seandurban.datasource.DataSource;

import java.util.Date;
import java.util.HashMap;

public class TTLCache<T> implements Cache {

    HashMap<String, T> cache;
    HashMap<String, Date> cacheEntryTime;

    DataSource<T> dataSource;

    int ttl;

    TTLCache(DataSource dataSource, int ttl) {
        this.dataSource = dataSource;
        this.ttl = ttl;
        this.cache = new HashMap<>();
        this.cacheEntryTime = new HashMap<>();
    }

    @Override
    public T retrieve(String key) {
        if(cache.containsKey(key)) {
            if(cacheEntryTime.containsKey(key) && new Date().getTime() - cacheEntryTime.get(key).getTime() < ttl) {
                return cache.get(key);
            }
            else {
                return retrieveAndAdd(key);
            }
        }
        return retrieveAndAdd(key);
    }

    T retrieveAndAdd(String key) {
        T dataEntry = dataSource.retrieve(key);
        cache.put(key, dataEntry);
        cacheEntryTime.put(key, new Date());
        return dataEntry;
    }
}
