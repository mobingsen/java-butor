package org.example.lru;

import java.util.LinkedHashMap;
import java.util.Map;

public class _1_LRU<K, V> {

    private static final float hashLoadFactory = 0.75f;
    private final LinkedHashMap<K, V> map;
    private final int cacheSize;

    public _1_LRU(int cacheSize) {
        this.cacheSize = cacheSize;
        int capacity = (int) Math.ceil(cacheSize / hashLoadFactory) + 1;
        map = new LinkedHashMap<K, V>(capacity, hashLoadFactory, true) {
            private static final long serialVersionUID = 1;

            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > _1_LRU.this.cacheSize;
            }
        };
    }

    public synchronized V get(K key) {
        return map.get(key);
    }

    public synchronized void put(K key, V value) {
        map.put(key, value);
    }

    public synchronized void clear() {
        map.clear();
    }

    public synchronized int usedSize() {
        return map.size();
    }
}
