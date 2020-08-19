package com.hawk.cellphone.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class Employee {
    private final Integer employeeId;
    private final String employeeName;
    private final LocalDate phonePurchaseDate;
    private final String phoneModel;

    private Employee() {
        this.employeeId = null;
        this.employeeName = null;
        this.phonePurchaseDate = null;
        this.phoneModel = null;
    }

    public Employee(final Integer employeeId, final String employeeName, final LocalDate phonePurchaseDate, final String phoneModel) {
        Optional<Integer> optId = Optional.ofNullable(employeeId);
        if(!optId.isPresent())
            throw new RuntimeException("Employee ID is required");

        this.employeeId = employeeId;
        this.phoneModel = phoneModel;
        this.employeeName = employeeName;
        this.phonePurchaseDate = phonePurchaseDate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return employeeName;
    }

    public LocalDate getPhonePurchaseDate() {
        return phonePurchaseDate;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public String toString() {

        StringBuilder bld = new StringBuilder();
        bld.append(String.format("Employee Id:%d Name:%s Model:%s Purshase Date:%tD %n",
                                 getEmployeeId(),
                                 getName(),
                                 getPhoneModel(),
                                 getPhonePurchaseDate()));
        return bld.toString();
    }
}
