package com.mycompany.database;

import java.util.*;

public interface Dao<T, K> {

    T findOne(K key) throws Exception;

    List<T> findAll() throws Exception;

    void delete(K key) throws Exception;
    
    void add(T o) throws Exception;
}
