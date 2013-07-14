package com.sapient.mapreduce.example.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheManager
{

    private static CacheManager      instance = new CacheManager();

    private Map<String, Cache<?, ?>> cacheMap = new ConcurrentHashMap<String, Cache<?, ?>>();

    private CacheManager()
    {

    }

    public static CacheManager getInstance()
    {
        return instance;
    }

    public <K, V> Cache<K, V> createCache(String name)
    {
        Cache<K, V> cache = new HashMapCacheImpl<K, V>();
        cacheMap.put(name, cache);
        return cache;
    }

    @SuppressWarnings("unchecked")
    public <K, V> Cache<K, V> getCache(String name)
    {
        return (Cache<K, V>) cacheMap.get(name);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static String getValue(String cacheName, String key)
    {
        String result = null;
        Cache cache = CacheManager.getInstance().getCache(cacheName);
        if (null != cache)
        {
            result = cache.get(key).toString();
        }
        return result;
    }
}
