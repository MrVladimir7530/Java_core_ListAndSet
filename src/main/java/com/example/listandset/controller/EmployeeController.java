package com.example.listandset.controller;

import com.example.listandset.exceptions.EmployeeAlreadyAddedException;
import com.example.listandset.exceptions.EmployeeNotFoundException;
import com.example.listandset.exceptions.EmployeeStorageIsFullException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.listandset.sevice.EmployeeServiceIml;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeServiceIml employeeServiceIml;

    public EmployeeController(EmployeeServiceIml employeeServiceIml) {
        this.employeeServiceIml = employeeServiceIml;
    }

    @GetMapping
    public String startDisplay(){
        return employeeServiceIml.startDisplay();
    }

    @GetMapping(path = "/add")
    public String add(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
        try {
            return "success: " + String.valueOf(employeeServiceIml.addEmployee(firstName, lastName));
        } catch (EmployeeAlreadyAddedException | EmployeeStorageIsFullException e ) {
            System.out.println(e.getMessage());
            return "<h1>" + e.getMessage() + "</h1>";
        }
    }

    @GetMapping(path = "/remove")
    public String remove(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
        if (employeeServiceIml.removeEmployee(firstName, lastName)) {
            return "success: " + firstName + " " + lastName + " remove";
        }
        return "this employee: " + firstName + " " + lastName +  " don't find";
    }

    @GetMapping(path =  "/get")
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

}
