package com.example.listandset.sevice;

import com.example.listandset.doMain.Employee;
import com.example.listandset.exceptions.EmployeeAlreadyAddedException;
import com.example.listandset.exceptions.EmployeeNotFoundException;
import com.example.listandset.exceptions.EmployeeStorageIsFullException;

import java.util.Collection;

public interface EmployeeService {
    String startDisplay();
    Employee addEmployee(String firstName, String lastName, int salary, int department) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException;
    boolean removeEmployee(String firstName, String lastName, int salary, int department);
    Employee getEmployee(int i) throws EmployeeNotFoundException;
     Collection<Employee> getAll();
}
