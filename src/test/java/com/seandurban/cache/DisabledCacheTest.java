package com.seandurban.cache;

import com.seandurban.cache.Cache;
import com.seandurban.cache.DisabledCache;
import com.seandurban.datasource.DataSource;
import com.seandurban.datasource.TestDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DisabledCacheTest {

    DataSource testData;

    @BeforeEach
    void setUp() {
        testData = new TestDataSource(0);
    }

    @Test
    void retrieve() {
        Cache<Integer> disabledCache = new DisabledCache(testData);

        int res1 = disabledCache.retrieve("key");
        assertEquals(res1,0);

        int res2 = disabledCache.retrieve("key");
        assertEquals(res2, 1);
    }
}