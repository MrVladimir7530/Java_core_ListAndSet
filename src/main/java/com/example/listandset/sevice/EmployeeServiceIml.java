package com.example.listandset.sevice;

import com.example.listandset.doMain.Employee;
import com.example.listandset.exceptions.EmployeeAlreadyAddedException;
import com.example.listandset.exceptions.EmployeeNotFoundException;
import com.example.listandset.exceptions.EmployeeStorageIsFullException;

import java.util.List;

public interface EmployeeServiceIml  {
    String startDisplay();
    Employee addEmployee(String firstName, String lastName) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException;
    boolean removeEmployee(String firstName, String lastName);
    Employee getEmployee(int i) throws EmployeeNotFoundException;

    public List<Employee> getAll();
}
