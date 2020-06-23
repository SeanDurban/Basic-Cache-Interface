package com.seandurban.datasource;

public class TestDataSource implements DataSource{

    int counter;

    public TestDataSource(int startCount) {
        this.counter = startCount;
    }

    public Object retrieve(String key) {
        return counter++;
    }
}
