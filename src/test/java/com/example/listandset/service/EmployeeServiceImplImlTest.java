package com.example.listandset.service;

import com.example.listandset.doMain.Employee;
import com.example.listandset.exceptions.EmployeeAlreadyAddedException;
import com.example.listandset.exceptions.EmployeeStorageIsFullException;
import com.example.listandset.sevice.EmployeeServiceImpl;
import com.example.listandset.sevice.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EmployeeServiceImplImlTest {

    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Test
    public void shouldAddEmployeeWhenNotExists() throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        Employee employee = new Employee("Ivan", "Ivanov", 100000, 1);
        employeeService.addEmployee("Ivan", "Ivanov", 100000, 1);

        Employee createdEmployee = employeeService.getEmployee(0);
        Assertions.assertEquals(employee, createdEmployee);
    }

    @Test
    public void shouldThrowExceptionAddExistedEmployee() throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        employeeService.addEmployee("Ivan", "Ivanov", 100000, 1);

        Assertions.assertThrows(EmployeeAlreadyAddedException.class, ()-> employeeService.addEmployee("Ivan", "Ivanov", 100000, 1));
    }

    @Test
    public void shouldRemoveEmployeeWhenExists() throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        employeeService.addEmployee("Ivan", "Ivanov", 100000, 1);
        boolean result = employeeService.removeEmployee("Ivan", "Ivanov", 100000, 1);

        Assertions.assertTrue(result);
    }

    @Test
    public void shouldGetAllEmployee() throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        employeeService.addEmployee("Ivan", "Ivanov", 100000, 1);
        employeeService.addEmployee("Masha", "Mironova", 80000, 1);
        employeeService.addEmployee("Dasha", "Yuriva", 90000, 1);
        employeeService.addEmployee("Petr", "Petrov", 110000, 2);

        Assertions.assertEquals(employeeList(), employeeService.getAll());
    }

    private List<Employee> employeeList() {
        return List.of(new Employee("Ivan", "Ivanov", 100000, 1),
                new Employee("Masha", "Mironova", 80000, 1),
                new Employee("Dasha", "Yuriva", 90000, 1),
                new Employee("Petr", "Petrov", 110000, 2));
    }

}
