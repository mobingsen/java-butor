package org.plus.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;

public class _1_LoadingCache {

    private static final LoadingCache<Long, String> loadingCache = CacheBuilder.newBuilder()
            .build(new CacheLoader<Long, String>() {
                @Override
                public String load(Long key) throws Exception {
                    return String.valueOf(key);
                }
            });

    public static void main(String[] args) throws ExecutionException {
        String s = loadingCache.get(1L);
        System.out.println(s);
    }
}
