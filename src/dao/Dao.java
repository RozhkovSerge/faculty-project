package dao;

import java.util.List;
import java.util.Optional;

public interface Dao <I, E>{
    Optional<E> findById(I id);

    List<E> findAll();

    boolean delete(I id);

    E save(E entity);

    boolean update(E entity);
}
