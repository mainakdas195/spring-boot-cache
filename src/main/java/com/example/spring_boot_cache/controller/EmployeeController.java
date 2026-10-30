package com.example.spring_boot_cache.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_cache.dto.EmployeeUpdateRequest;
import com.example.spring_boot_cache.entity.Employee;
import com.example.spring_boot_cache.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployee(
            @PathVariable Integer employeeId) {

        return employeeService.getEmployeeById(employeeId);
    }
    
    @PutMapping("/{employeeId}")
    public Employee updateEmployee(
            @PathVariable Integer employeeId,
            @RequestBody EmployeeUpdateRequest request) {

        return employeeService.updateEmployee(employeeId, request);
    }
    
    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(
            @PathVariable Integer employeeId) {

        employeeService.deleteEmployee(employeeId);
        return "Employee deleted successfully: " + employeeId;
    }
    
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }
}