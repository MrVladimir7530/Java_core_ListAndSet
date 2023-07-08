package com.example.listandset.controller;

import com.example.listandset.exceptions.EmployeeAlreadyAddedException;
import com.example.listandset.exceptions.EmployeeNotFoundException;
import com.example.listandset.exceptions.EmployeeStorageIsFullException;
import com.example.listandset.sevice.EmployeeDepartment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.listandset.sevice.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeDepartment employeeDepartment;

    public EmployeeController(EmployeeService employeeService, EmployeeDepartment employeeDepartment) {
        this.employeeService = employeeService;
        this.employeeDepartment = employeeDepartment;
    }

    @GetMapping
    public String startDisplay(){
        return employeeService.startDisplay();
    }

    @GetMapping(path = "/add")
    public String add(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("salary") int salary, @RequestParam("department") int department){
        try {
            return "success: " + String.valueOf(employeeService.addEmployee(firstName, lastName, salary, department));
        } catch (EmployeeAlreadyAddedException | EmployeeStorageIsFullException e ) {
            System.out.println(e.getMessage());
            return "<h1>" + e.getMessage() + "</h1>";
        }
    }

    @GetMapping(path = "/remove")
    public String remove(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("salary") int salary, @RequestParam("department") int department){
        if (employeeService.removeEmployee(firstName, lastName, salary, department)) {
            return "success: " + firstName + " " + lastName + " remove";
        }
        return "this employee: " + firstName + " " + lastName +  " don't find";
    }

    @GetMapping("/get")
    public String get(@RequestParam("number") Integer number){
        try {
            return String.valueOf(employeeService.getEmployee(number));
        } catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
            return "<h1>" + e.getMessage() + "</h1>";
        }
    }
    @GetMapping(path =  "/getall")
    public String getAll(){
        return employeeService.getAll().toString();
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
