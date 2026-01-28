
### 1️⃣ Department

```java
Department
- id
- name
```

### 2️⃣ Employee

```java
Employee
- id
- name
- salary
- department (ManyToOne)
```

### 3️⃣ Project

```java
Project
- id
- name
- employee (ManyToOne)
```

---

## Relationships

```
Department 1 ---- * Employee 1 ---- * Project
```

In Java terms:

```java
employee.getDepartment()
project.getEmployee().getDepartment()
```

---

# 🎯 BUSINESS REQUIREMENT (MEDIUM–COMPLEX)

> **“Find all projects where**
> • employee salary > 50,000
> • employee belongs to IT department
> • project name contains ‘AI’”**

This query **touches all 3 tables**.

---

# 1️⃣ SQL VERSION (WHAT DB UNDERSTANDS)

```sql
SELECT p.*
FROM project p
JOIN employee e ON p.employee_id = e.id
JOIN department d ON e.department_id = d.id
WHERE e.salary > 50000
  AND d.name = 'IT'
  AND p.name LIKE '%AI%';
```

This is the **ground truth**.

---

# 2️⃣ JPQL VERSION (WHAT JPA UNDERSTANDS)

```jpql
SELECT p
FROM Project p
JOIN p.employee e
JOIN e.department d
WHERE e.salary > :salary
  AND d.name = :deptName
  AND p.name LIKE :projectName
```

### Key thing to explain to students

* JPQL uses **entity names**, not tables
* JPQL uses **object navigation**, not foreign keys
* `JOIN p.employee` means:

  ```java
  p.getEmployee()
  ```

---

# 3️⃣ SPRING DATA METHOD NAME (WHAT YOU ASKED FOR)

### Repository: `ProjectRepository`

```java
List<Project> findByEmployeeSalaryGreaterThanAndEmployeeDepartmentNameAndNameContaining(
        double salary,
        String departmentName,
        String projectName
);
```

---

## 🔍 BREAKING THE METHOD NAME (THIS IS THE KEY TEACHING PART)

Let’s parse it **left to right**.

### Method name

```
findBy
EmployeeSalaryGreaterThan
And
EmployeeDepartmentName
And
NameContaining
```

---

### How Spring interprets it

#### `EmployeeSalaryGreaterThan`

```java
project.getEmployee().getSalary() > ?
```

#### `EmployeeDepartmentName`

```java
project.getEmployee().getDepartment().getName() = ?
```

#### `NameContaining`

```java
project.getName().contains(?)
```

---
---

# 🧪 OPTIONAL: SAME QUERY USING DTO (BEST PRACTICE)

### JPQL DTO version

```jpql
SELECT new ProjectSummaryDTO(
    p.name,
    e.name,
    d.name
)
FROM Project p
JOIN p.employee e
JOIN e.department d
WHERE e.salary > :salary
  AND d.name = :dept
  AND p.name LIKE :project
```

Much cleaner for APIs.

---

