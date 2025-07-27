package com.example.currencyexchange.dao;

import java.util.List;

public interface Dao<K,E> {
    boolean update(E e);
    List<E> findAll();
    E save(E e);
    boolean delete(K id);
}
