package com.orion.DigiWallet.jpaTest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}


//findByName(String name)
//jpql : SELECT e FROM Employee e WHERE e.name = :name
//sql : SELECT * FROM employees WHERE name = ?

