package com.orion.DigiWallet.jpaTest;

public class EmployeeDepartmentDTO {

    private String employeeName;
    private String departmentName;

    public EmployeeDepartmentDTO(String employeeName, String departmentName) {
        this.employeeName = employeeName;
        this.departmentName = departmentName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}
