package taranova.spring_rest.repository;

import taranova.spring_rest.entity.Employee;
import java.util.List;

public interface Repo<E> {
    List<E> getAllEntities();
    void saveOrUpdateEntity(E e);
    Employee findEntityById(int id);
    void deleteEntity(E entity);
}
