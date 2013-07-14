package com.sapient.mapreduce.example.cache;

import java.util.Set;

public interface Cache<K, V>
{
    public V get(K key);
    
    public void put(K key, V value);
    
    public int size();
    
    public Set<K> getKeys();
    
    public void clear();
}
