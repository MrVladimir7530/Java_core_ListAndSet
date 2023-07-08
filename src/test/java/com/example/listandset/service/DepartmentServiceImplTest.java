package com.example.listandset.service;

import com.example.listandset.doMain.Employee;
import com.example.listandset.sevice.DepartmentService;
import com.example.listandset.sevice.EmployeeDepartment;
import com.example.listandset.sevice.EmployeeServiceImpl;
import com.example.listandset.sevice.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class DepartmentServiceImplTest {

    private EmployeeDepartment employeeDepartment;


//    @BeforeEach
   /* public void setUp() {
        EmployeeServiceIml employeeService = new EmployeeService();
        employeeService.addEmployee("Ivan", "Ivanov", 100000, 1);

        for (Employee employee : employeeList()) {
            employeeService.addEmployee(employee);
        }

        employeeDepartment = new DepartmentService(employeeService);
    }*/

  @BeforeEach
  public void setUp() {
      EmployeeServiceImpl employeeService = mock(EmployeeServiceImpl.class);
      when(employeeService.getAll()).thenReturn(employeeList());

      employeeDepartment = new DepartmentService(employeeService);
    }



    private List<Employee> employeeList() {
        return List.of(new Employee("Ivan", "Ivanov", 100000, 1),
                new Employee("Masha", "Mironova", 80000, 1),
                new Employee("Dasha", "Yuriva", 90000, 1),
                new Employee("Petr", "Petrov", 110000, 2));
    }

    @Test
    public void shouldReturnEmployeeWithMaxSalaryFromDepartment() {
        Employee employee = employeeDepartment.maxSalaryByDepartment(1);

        Assertions.assertEquals("Ivan", employee.getFirstName());
        Assertions.assertEquals("Ivanov", employee.getLastName());
        Assertions.assertEquals(100000, employee.getSalary());
        Assertions.assertEquals(1, employee.getDepartment());
    }

    @Test
    public void shouldReturnEmployeeWithMinSalaryFromDepartment() {
        Employee employee = employeeDepartment.minSalaryByDepartment(1);

        Assertions.assertEquals("Masha", employee.getFirstName());
        Assertions.assertEquals("Mironova", employee.getLastName());
        Assertions.assertEquals(80000, employee.getSalary());
        Assertions.assertEquals(1, employee.getDepartment());
    }

    @Test
    public void shouldReturnListEmployeeByDepartment() {
        List<Employee> listResult = employeeDepartment.displayEmployeeByDepartment(1);

        List<Employee> listTrue = List.of(new Employee("Ivan", "Ivanov", 100000, 1),
                new Employee("Masha", "Mironova", 80000, 1),
                new Employee("Dasha", "Yuriva", 90000, 1));

        Assertions.assertEquals(listTrue, listResult);
    }

    @Test
    public void shouldReturnMapByDepartment() {
        Map<Integer, List<Employee>> mapResult = employeeDepartment.displayAllEmployeeByDepartment();
        List<Employee> listDepartment1 = List.of(new Employee("Ivan", "Ivanov", 100000, 1),
                new Employee("Masha", "Mironova", 80000, 1),
                new Employee("Dasha", "Yuriva", 90000, 1));
        List<Employee> listDepartment2 = List.of(new Employee("Petr", "Petrov", 110000, 2));
        Map<Integer, List<Employee>> mapTrue = Map.of(1, listDepartment1, 2, listDepartment2);

        Assertions.assertEquals(mapTrue, mapResult);
    }
}
