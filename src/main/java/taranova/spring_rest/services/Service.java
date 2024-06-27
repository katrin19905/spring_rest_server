package taranova.spring_rest.services;

import taranova.spring_rest.entity.Employee;
import java.util.List;

public interface Service<E> {

    List<E> getAllEntities();
    void saveOrUpdateEntity(Employee employee);
    Employee findEntityById(int id);
    void deleteEntity(E entity);
}
