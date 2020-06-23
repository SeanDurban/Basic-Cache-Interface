package com.seandurban.cache;

import com.seandurban.datasource.DataSource;
import com.seandurban.datasource.TestDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LimitedMemoryCacheTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void retrieve() {
        DataSource testData = new TestDataSource(0);
        LimitedMemoryCache<Integer> cache = new LimitedMemoryCache<>(testData, 1);

        assertEquals(0, cache.retrieve("key1"));
        //From cache
        assertEquals(0, cache.retrieve("key1"));
        //Retrieve unique key
        assertEquals(1, cache.retrieve("key2"));
        // Retrieve unique, ensure evicted
        assertEquals(1, cache.retrieve("key2"));
        assertEquals(2, cache.retrieve("key1"));
    }
}