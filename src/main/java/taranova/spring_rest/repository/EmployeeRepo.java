package taranova.spring_rest.repository;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import taranova.spring_rest.entity.Employee;
import java.util.List;

@Repository
@Setter
@Getter
public class EmployeeRepo implements Repo<Employee> {
@Autowired
    private LocalSessionFactoryBean sessionFactory;

    public EmployeeRepo() {
    }

    @Override
    public List<Employee> getAllEntities() {
        Session session = sessionFactory.getObject().openSession();
        Query<Employee> query = session.createQuery("from Employee"
                , Employee.class);
        List<Employee> allEmp = query.getResultList();

        return allEmp;
    }

    @Override
    public void saveOrUpdateEntity(Employee employee) {
        Session session = sessionFactory.getObject().openSession();
        session.beginTransaction();
        session.saveOrUpdate(employee);
        session.getTransaction().commit();
        System.out.println("saved employee in repo");

    }

    @Override
    public Employee findEntityById(int id) {
        Session session = sessionFactory.getObject().openSession();
        session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        session.getTransaction().commit();
        return employee;
    }

    @Override
    public void deleteEntity(Employee entity) {
        Session session = sessionFactory.getObject().openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }
}