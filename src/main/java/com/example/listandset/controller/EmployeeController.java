package com.example.listandset.controller;

import com.example.listandset.doMain.Employee;
import com.example.listandset.exceptions.EmployeeAlreadyAddedException;
import com.example.listandset.exceptions.EmployeeNotFoundException;
import com.example.listandset.exceptions.EmployeeStorageIsFullException;
import com.example.listandset.sevice.EmployeeDepartment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.listandset.sevice.EmployeeServiceIml;

import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeServiceIml employeeServiceIml;
    private final EmployeeDepartment employeeDepartment;

    public EmployeeController(EmployeeServiceIml employeeServiceIml, EmployeeDepartment employeeDepartment) {
        this.employeeServiceIml = employeeServiceIml;
        this.employeeDepartment = employeeDepartment;
    }

    @GetMapping
    public String startDisplay(){
        return employeeServiceIml.startDisplay();
    }

    @GetMapping(path = "/add")
    public String add(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("salary") int salary, @RequestParam("department") int department){
        try {
            return "success: " + String.valueOf(employeeServiceIml.addEmployee(firstName, lastName, salary, department));
        } catch (EmployeeAlreadyAddedException | EmployeeStorageIsFullException e ) {
            System.out.println(e.getMessage());
            return "<h1>" + e.getMessage() + "</h1>";
        }
    }

    @GetMapping(path = "/remove")
    public String remove(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("salary") int salary, @RequestParam("department") int department){
        if (employeeServiceIml.removeEmployee(firstName, lastName, salary, department)) {
            return "success: " + firstName + " " + lastName + " remove";
        }
        return "this employee: " + firstName + " " + lastName +  " don't find";
    }

    public String get(@RequestParam("number") Integer number){
        try {
            return String.valueOf(employeeServiceIml.getEmployee(number));
        } catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
            return "<h1>" + e.getMessage() + "</h1>";
        }
    }
    @GetMapping(path =  "/getall")
    public String getAll(){
        return employeeServiceIml.getAll().toString();
    }

//    @GetMapping(path = "departments/max-salary")
//    public Employee maxSalaryByDepartment(@RequestParam("department") int department) {
//       return employeeDepartment.maxSalaryByDepartment(department);
//    }
//
//     @GetMapping(path = "departments/min-salary")
//    public Employee minSalaryByDepartment(@RequestParam("department") int department) {
//       return employeeDepartment.minSalaryByDepartment(department);
//    }



}
