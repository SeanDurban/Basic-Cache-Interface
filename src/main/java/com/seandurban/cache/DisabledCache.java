package com.seandurban.cache;

import com.seandurban.datasource.DataSource;

public class DisabledCache implements Cache {

    DataSource dataSource;

    public DisabledCache(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public Object retrieve(String key) {
        return dataSource.retrieve(key);
    }
}

