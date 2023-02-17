package com.amano.moeconn.config.component;

public interface AbstractCacheProcessor<T, R> {
    R getCacheByKey(T key);

    void loadCache();
}
