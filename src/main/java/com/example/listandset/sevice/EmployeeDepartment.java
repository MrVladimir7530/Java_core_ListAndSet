package com.example.listandset.sevice;

import com.example.listandset.doMain.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeDepartment {
    Employee maxSalaryByDepartment(int department);
    Employee minSalaryByDepartment(int department);
    List<Employee> displayEmployeeByDepartment(int department);
    Map<Integer, List<Employee>> displayAllEmployeeByDepartment();

}
