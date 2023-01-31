package myProject.web.dao;

import java.util.List;

public interface Dao<I,E>{
    List<E> findAll();

    E create(E e);

    boolean update(String s);

    boolean delete(I i);
}
