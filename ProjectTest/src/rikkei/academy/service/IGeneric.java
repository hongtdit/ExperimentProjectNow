package rikkei.academy.service;

import java.util.List;

public interface IGeneric<T> {
    List<T> findAll();

    void save(T t);

    void remove(int id);

    T findById(int id);
}
