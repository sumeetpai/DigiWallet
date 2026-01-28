package com.orion.DigiWallet.jpaTest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    List<Employee> findByDepartmentId(Department department);

    @Query("""
            select new com.orion.DigiWallet.jpaTest.EmployeeDepartmentDTO(e.name, d.name)
            from Employee e
            join e.department d 
            """)
    List<EmployeeDepartmentDTO> fetchEmployeeDepartmentName();

        @Query("""
        SELECT e
        FROM Employee e
        JOIN e.department d
        WHERE d.name = :name
        """)
    List<Employee> findByDepartmentName(String name);
}

//This is JPQL (Java Persistence Query Language) — not SQL.
//JPQL works on:
//        Java classes, not tables
//        Java fields, not columns
//       Java relationships, not foreign keys

//SELECT new com.orion.DigiWallet.dto.EmployeeDepartmentDTO(
//        e.name,
//        d.name
//        )


//This is called a JPQL constructor expression.
//What it does
//Instead of returning:
//Employee
//        Department
//Object graphs
//It returns custom objects created on the fly.

//“For each Employee e, bring the related Department object and call it d.
// no need for join on condition because relationship is already defined in the entity classes.
//employees.department_id → department.id
