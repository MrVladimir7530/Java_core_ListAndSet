package com.example.listandset.sevice;

import com.example.listandset.doMain.Employee;
import com.example.listandset.exceptions.EmployeeAlreadyAddedException;
import com.example.listandset.exceptions.EmployeeNotFoundException;
import com.example.listandset.exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService implements EmployeeServiceIml {
    List<Employee> employees = new ArrayList<>();

    public String startDisplay(){
        return "<h1>Добро пожаловать</h1>";
    }
    public Employee addEmployee(String firstName, String lastName) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            add(employee);
            return employee;
        }
        else {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }
    }

    private void add(Employee employee) throws EmployeeStorageIsFullException{
        if(employees.size()<10){
            employees.add(employee);
        } else {
            throw new EmployeeStorageIsFullException("Список переполнен");
        }
    }

    public boolean removeEmployee(String firstName, String lastName){
        Employee employee = new Employee(firstName, lastName);
        return employees.remove(employee);
    }

    public Employee getEmployee(int i) throws EmployeeNotFoundException {
        if (employees.size() > i) {
            return employees.get(i);
        } else {
            throw new EmployeeNotFoundException("Индекс превышает значение");
        }
    }
    public List<Employee> getAll() {
            return employees;
    }
}
