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
        LimitedMemoryCache cache = new LimitedMemoryCache(testData, 1);

        assertEquals(cache.retrieve("key1"), 0);
        //From cache
        assertEquals(cache.retrieve("key1"), 0);
        //Retrieve unique key
        assertEquals(cache.retrieve("key2"), 1);
        // Retrieve unique, ensure evicted
        assertEquals(cache.retrieve("key2"), 1);
        assertEquals(cache.retrieve("key1"), 2);
    }
}