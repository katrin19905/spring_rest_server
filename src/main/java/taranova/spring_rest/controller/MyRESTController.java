package taranova.spring_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import taranova.spring_rest.entity.Employee;
import taranova.spring_rest.exception_handling.EmployeeIsAlreadyInDBException;
import taranova.spring_rest.exception_handling.NoSuchEmployeeException;
import taranova.spring_rest.services.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {
    @Autowired
    private EmployeeService employeeService;

    public MyRESTController() {
    }

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEntities();
        return allEmployees;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = checkEmployeeInDatabaseForViewOrUpdateOrDelete(id);
        return employee;
    }

    @PostMapping("/employees")
    public Employee saveNewEmployee(@RequestBody Employee employee) {
        checkEmployeeNotInDBForAddNewEmployee(employee);
        employeeService.saveOrUpdateEntity(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
       checkEmployeeInDatabaseForViewOrUpdateOrDelete(employee.getId());
        employeeService.saveOrUpdateEntity(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee employee = checkEmployeeInDatabaseForViewOrUpdateOrDelete(id);
        employeeService.deleteEntity(employee);
        return "Employee with ID = " + id + " was deleted";
    }

    private Employee checkEmployeeInDatabaseForViewOrUpdateOrDelete(
            int id) {
        Employee employee = employeeService.findEntityById(id);
        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee with id "
                    + id + " in DataBase");
        }
        return employee;
    }

    private void checkEmployeeNotInDBForAddNewEmployee(Employee employee) {
        int employeeId = employee.getId();
        if (employeeService.findEntityById(employeeId) != null) {
            throw new EmployeeIsAlreadyInDBException("There is employee with id "
                    + employeeId + " in DataBase");
        }
    }
}
