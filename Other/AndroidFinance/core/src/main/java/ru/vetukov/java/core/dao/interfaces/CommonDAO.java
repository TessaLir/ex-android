package ru.vetukov.java.core.dao.interfaces;

import java.util.List;

/**
 * Описывает ощие дейтвия с БД для всех объектов.
 * @param <T>
 */
public interface CommonDAO<T> {

    List<T> getAll();
    T get(long id);
    boolean update(T storage); // boolean - что бы удостовериться, что операция прошла успешно
    boolean delete(T storage);
    boolean add(T object);

}
