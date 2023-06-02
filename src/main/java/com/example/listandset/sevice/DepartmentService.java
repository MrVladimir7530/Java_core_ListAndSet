package com.example.listandset.sevice;

import com.example.listandset.doMain.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService implements EmployeeDepartment {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee maxSalaryByDepartment(int department) {
        return employeeService.getAll()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingInt(e -> e.getSalary()))
                .orElseThrow(() -> new IllegalArgumentException("No employee in department"));
    }

    @Override
    public Employee minSalaryByDepartment(int department) {
        return employeeService.getAll()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingInt(e -> e.getSalary()))
                .orElseThrow(() -> new IllegalArgumentException("No employee in department"));
    }

    @Override
    public List<Employee> displayEmployeeByDepartment(int department) {
       return employeeService.getAll()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> displayAllEmployeeByDepartment() {
        return employeeService.getAll()
                .stream()
                .collect(Collectors.groupingBy(e->e.getDepartment()));
    }
}
