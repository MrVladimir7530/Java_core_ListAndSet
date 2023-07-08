package com.example.listandset.controller;

import com.example.listandset.doMain.Employee;
import com.example.listandset.sevice.EmployeeDepartment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final EmployeeDepartment employeeDepartment;
    public DepartmentController(EmployeeDepartment employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }

    @GetMapping(path = "/max-salary")
    public Employee maxSalaryByDepartment(@RequestParam("department") int department){
        return employeeDepartment.maxSalaryByDepartment(department);
    }

    @GetMapping(path = "/min-salary")
    public Employee minSalaryByDepartment(@RequestParam("department") int department){
        return employeeDepartment.minSalaryByDepartment(department);
    }

    @GetMapping(value = "all", params = "department")
    public List<Employee> displayEmployeeByDepartment(@RequestParam("department") int department){
        return employeeDepartment.displayEmployeeByDepartment(department);
    }
    @GetMapping("all")
    public Map<Integer,List<Employee>> displayAllEmployeeByDepartment(){
        return employeeDepartment.displayAllEmployeeByDepartment();
    }

}
