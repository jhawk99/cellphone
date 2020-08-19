package com.hawk.cellphone.domain.report.model;

import com.hawk.cellphone.domain.entity.CellphoneUse;
import com.hawk.cellphone.domain.entity.Employee;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CellphoneUsageData {


    private int year;

    private double totalMinutes;

    private double totalData;

    private Map<Integer,CellphoneUsageDetailData> detailData;

    private CellphoneUsageData() {
    }

    public CellphoneUsageData(final int year) {
        this.year = year;
        totalMinutes = BigDecimal.ZERO.doubleValue();
        totalData = BigDecimal.ZERO.doubleValue();
        detailData = new HashMap<>();

    }


    public void updateUsage(final Employee employee,final CellphoneUse use) {
        Employee e = employee;
        if(e == null)
            e = getUnknown();

        CellphoneUsageDetailData detail = getDetailData(e);
        updateTotals(use);
        detail.updateUsage(use);

    }

    private Employee getUnknown() {
        return new Employee(Integer.MIN_VALUE,"Unknown",null,"Unknown");
    }

    private CellphoneUsageDetailData getDetailData(final Employee e) {
        if(this.detailData.containsKey(e.getEmployeeId()))
            return detailData.get(e.getEmployeeId());

        CellphoneUsageDetailData data = new CellphoneUsageDetailData(e);
        detailData.put(e.getEmployeeId(),data);
        return data;

    }

    private void updateTotals(final CellphoneUse use) {
        this.totalMinutes += use.getMinutesUsed();
        this.totalData += use.getDataUsed();

    }

    public Date getRunDate() {
        return Calendar.getInstance().getTime();
    }

    public int getNumberOfPhones() {
        return detailData.values().size();
    }

    public int getYear() {
        return year;
    }

    public double getTotalMinutes() {
        return totalMinutes;
    }

    public double getTotalData() {
        return totalData;
    }

    public double getAverageMinutes() {
        return this.getTotalMinutes() / getNumberOfPhones();
    }

    public double getAverageData() {
        return this.getTotalData() / getNumberOfPhones();
    }

    public Map<Integer,CellphoneUsageDetailData> getDetailData() {
        return detailData;
    }

    public String toString() {
        StringBuilder bld = new StringBuilder();
        bld.append(String.format("Report Run Date: %tD %n ",getRunDate()));
        bld.append(String.format("Number of Phones: %d %n ",getNumberOfPhones()));
        bld.append(String.format("Total Minutes: %f %n ",getTotalMinutes()));
        bld.append(String.format("Total Data: %f %n ",getTotalData()));
        bld.append(String.format("Average Minutes: %f %n ",getAverageMinutes()));
        bld.append(String.format("Average Data: %f %n ",getAverageData()));
        this.getDetailData().values().forEach( (i) -> { bld.append(i.toString()); });
        return bld.toString();
    }
}
