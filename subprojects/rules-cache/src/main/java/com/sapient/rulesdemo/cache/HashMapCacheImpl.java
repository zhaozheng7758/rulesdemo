package com.sapient.rulesdemo.cache;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

class HashMapCacheImpl<K,V> implements Cache<K,V>
{
    
    private Map<K,V> map = new ConcurrentHashMap<K,V>();

    @Override
    public V get(K key)
    {
        return map.get(key);
    }

    @Override
    public void put(K key, V value)
    {
        map.put(key, value);
    }
    
    @Override
    public int size()
    {
        return map.size();
    }

    @Override
    public Set<K> getKeys()
    {
        return map.keySet();
    }

    @Override
    public void clear()
    {
        map.clear();
    }
}
