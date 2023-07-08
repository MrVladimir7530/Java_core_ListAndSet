package com.example.listandset.sevice;

import com.example.listandset.doMain.Employee;
import com.example.listandset.exceptions.EmployeeAlreadyAddedException;
import com.example.listandset.exceptions.EmployeeNotFoundException;
import com.example.listandset.exceptions.EmployeeStorageIsFullException;
import com.example.listandset.exceptions.InvalidInputException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    List<Employee> employees = new ArrayList<>();

    @Override
    public String startDisplay() {
        return "<h1>Добро пожаловать</h1>";
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int salary, int department) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        if (!validate(firstName, lastName)) {
            throw new InvalidInputException();
        }
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
        if (!validate(firstName, lastName)) {
            throw new InvalidInputException();
        }
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

    private boolean validate(String firstName, String lastName) {
        return StringUtils.isAlpha(firstName) && StringUtils.isAlpha(lastName);
    }
}
