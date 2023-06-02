package com.example.listandset.sevice;

import com.example.listandset.doMain.Employee;
import com.example.listandset.exceptions.EmployeeAlreadyAddedException;
import com.example.listandset.exceptions.EmployeeNotFoundException;
import com.example.listandset.exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService implements EmployeeServiceIml {
    List<Employee> employees = new ArrayList<>();

    @Override
    public String startDisplay() {
        return "<h1>Добро пожаловать</h1>";
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int salary, int department) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (!employees.contains(employee)) {
            add(employee);
            return employee;
        } else {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }
    }

    private void add(Employee employee) throws EmployeeStorageIsFullException {
        if (employees.size() < 10) {
            employees.add(employee);
        } else {
            throw new EmployeeStorageIsFullException("Список переполнен");
        }
    }

    @Override
    public boolean removeEmployee(String firstName, String lastName, int salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        return employees.remove(employee);
    }

    @Override
    public Employee getEmployee(int i) throws EmployeeNotFoundException {
        if (employees.size() > i) {
            return employees.get(i);
        } else {
            throw new EmployeeNotFoundException("Индекс превышает значение");
        }
    }
    @Override
    public List<Employee> getAll() {
        return employees;
    }
}
