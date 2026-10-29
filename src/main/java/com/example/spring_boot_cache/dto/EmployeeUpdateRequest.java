package com.example.spring_boot_cache.dto;

import java.math.BigDecimal;

public class EmployeeUpdateRequest {

    private String name;
    private BigDecimal phoneNumber;

    public EmployeeUpdateRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(BigDecimal phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}