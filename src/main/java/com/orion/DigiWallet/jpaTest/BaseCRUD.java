package com.orion.DigiWallet.jpaTest;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class BaseCRUD {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public BaseCRUD(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public void insertDepartment(){
        Department department = new Department();
        department.setName("IT");
        departmentRepository.save(department);
    }

    public void moreDataInsert(){
        // ---------- Department 1 ----------
        Department ac = new Department();
        ac.setName("ACC");
        departmentRepository.save(ac);

        // ---------- Department 2 ----------
        Department hr = new Department();
        hr.setName("HR");
        departmentRepository.save(hr);

        // ---------- Employees for IT ----------
        Employee e1 = new Employee();
        e1.setName("Alice");
        e1.setDepartment(ac);

        Employee e2 = new Employee();
        e2.setName("Bob");
        e2.setDepartment(ac);

        // ---------- Employees for HR ----------
        Employee e3 = new Employee();
        e3.setName("Charlie");
        e3.setDepartment(hr);

        Employee e4 = new Employee();
        e4.setName("Diana");
        e4.setDepartment(hr);

        employeeRepository.save(e1);
        employeeRepository.save(e2);
        employeeRepository.save(e3);
        employeeRepository.save(e4);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
}
