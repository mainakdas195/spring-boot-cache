package com.example.spring_boot_cache.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring_boot_cache.dto.EmployeeUpdateRequest;
import com.example.spring_boot_cache.entity.Employee;
import com.example.spring_boot_cache.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //This key is a unique Id #employeeId is the method argument. This can be key = "'employee_' + #employeeId" Ex:employee_100
    @Cacheable(
        cacheNames = "employees",
        key = "#employeeId"
    )
    public Employee getEmployeeById(Integer employeeId) {

        System.out.println(
            "Fetching employee from MySQL: " + employeeId);

        return employeeRepository.findByEmployeeId(employeeId)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Employee not found: " + employeeId
            ));
    }
    

    // UPDATE employee: update database and refresh cache
    @Transactional
    @CachePut(cacheNames = "employees", key = "#employeeId")
    public Employee updateEmployee(
            Integer employeeId,
            EmployeeUpdateRequest request) {

        System.out.println("Updating employee in MySQL: " + employeeId);

        Employee employee = employeeRepository
            .findByEmployeeId(employeeId)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Employee not found: " + employeeId
            ));

        employee.setName(request.getName());
        employee.setPhoneNumber(request.getPhoneNumber());

        return employeeRepository.save(employee);
    }
    
    @Transactional
    @CacheEvict(cacheNames = "employees", key = "#employeeId")
    public void deleteEmployee(Integer employeeId) {

        System.out.println("Deleting employee from MySQL: " + employeeId);

        Employee employee = employeeRepository
            .findByEmployeeId(employeeId)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Employee not found: " + employeeId
            ));

        employeeRepository.delete(employee);
    }
}