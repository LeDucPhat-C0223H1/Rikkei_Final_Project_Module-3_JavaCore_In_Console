package bussiness.service;

import java.util.List;

public interface IGeneric<T, E> {
    List<T> findAll();

    T findById(E id);

    boolean save(T t);

    void deleteById(E id);
}
