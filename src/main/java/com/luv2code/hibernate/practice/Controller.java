package com.luv2code.hibernate.practice;

import com.luv2code.hibernate.practice.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Controller {

    private SessionFactory sessionFactory;

    public Controller() {
        this.sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
    }

    public void closeConnection() {
        sessionFactory.close();
    }

    public void addEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(employee);
        session.getTransaction().commit();
    }

    public Employee getEmployeeById(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        session.getTransaction().commit();

        return employee;
    }

    public List<Employee> getEmployeeByCompany(String companyName) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Employee> employees = session.createQuery("from Employee e where e.company LIKE '"
                + companyName + "'").getResultList();
        session.getTransaction().commit();

        return employees;
    }

    public void deleteEmployeeById(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete from Employee where id=" + id).executeUpdate();
        session.getTransaction().commit();
    }
}
