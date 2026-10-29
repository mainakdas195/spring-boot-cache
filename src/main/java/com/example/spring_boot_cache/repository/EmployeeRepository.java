package com.example.spring_boot_cache.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_boot_cache.entity.Employee;

public interface EmployeeRepository
        extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByEmployeeId(Integer employeeId);
}