package com.files.utils;

import java.util.Set;

public interface MultiKeyMap<V> {
    V put (String md5, String name, V value);

    V getByMD5 (String md5);

    Set<V> getByName (String name);

    V remove(String md5);

//    int size();
//
//    boolean isEmpty();
//
//    boolean containsKey(Object var1);
//
//    boolean containsValue(Object var1);
//
//    V get(Object var1);
//
//    V put(K var1, V var2);
//
//    V remove(Object var1);
//
//    void putAll(Map<? extends K, ? extends V> var1);
//
//    void clear();
//
//    Set<K> keySet();
//
//    Collection<V> values();
//
//    Set<Map.Entry<K, V>> entrySet();
//
//    boolean equals(Object var1);
//
//    int hashCode();
//
//    public interface Entry<K, V> {
//        K getKey();
//
//        V getValue();
//
//        V setValue(V var1);
//
//        boolean equals(Object var1);
//
//        int hashCode();
//    }
}
