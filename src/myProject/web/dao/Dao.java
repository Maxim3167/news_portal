package myProject.web.dao;

import myProject.web.model.User;

import java.util.List;
import java.util.Optional;

public interface Dao<I,E>{
    List<E> findAll();

    E create(E e);

    boolean update(String s);

    boolean delete(I i);

    Optional<E> findById(Integer id);


}
