package taranova.spring_rest.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import taranova.spring_rest.entity.Employee;
import taranova.spring_rest.repository.EmployeeRepo;
import java.util.List;

@org.springframework.stereotype.Service
@Setter
@Getter
public class EmployeeService
        implements taranova.spring_rest.services.Service<Employee> {

@Autowired
    private EmployeeRepo employeeRepo;

    public EmployeeService() {
    }

    @Override
    public List<Employee> getAllEntities() {
        return employeeRepo.getAllEntities();
    }

    @Override
    public void saveOrUpdateEntity(Employee employee) {
        employeeRepo.saveOrUpdateEntity(employee);
        System.out.println("saved employee in service");
    }

    @Override
    public Employee findEntityById(int id) {
        return employeeRepo.findEntityById(id);
    }

    @Override
    public void deleteEntity(Employee entity) {
        employeeRepo.deleteEntity(entity);
    }
}