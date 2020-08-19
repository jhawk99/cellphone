package com.hawk.cellphone.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class CellphoneUse {

    private final LocalDate date;

    private final double minutesUsed;

    private final double dataUsed;

    private Integer employeeId;

    private CellphoneUse() {
        this.employeeId = null;
        this.date = null;
        this.minutesUsed = BigDecimal.ZERO.doubleValue();
        this.dataUsed = BigDecimal.ZERO.doubleValue();
    }

    public CellphoneUse(final Integer employeeId,final LocalDate date,final double minutesUsed,
                        final double dataUsed) {
        Optional<Integer> optId = Optional.ofNullable(employeeId);
        if(!optId.isPresent())
            throw new RuntimeException("Employee ID is required");
        this.employeeId = employeeId;
        Optional<LocalDate> optDate = Optional.ofNullable(date);
        if(!optDate.isPresent())
            throw new RuntimeException("Date is required");
        this.date = date;
        this.minutesUsed = minutesUsed;
        this.dataUsed = dataUsed;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public int getYear() {
        return date.getYear();
    }

    public int getMonth() {
        return date.getMonth().getValue();
    }

    public double getMinutesUsed() {
        return minutesUsed;
    }

    public double getDataUsed() {
        return dataUsed;
    }
}

