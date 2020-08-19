package com.hawk.cellphone.domain.report.model;

import com.hawk.cellphone.domain.entity.CellphoneUse;
import com.hawk.cellphone.domain.entity.Employee;

import java.util.Arrays;

public class CellphoneUsageDetailData {

    private Employee employee;

    private double[] totalMinutes = new double[12];

    private double[] totalData = new double[12];

    private CellphoneUsageDetailData() {
    }

    public CellphoneUsageDetailData(final Employee employee) {
        this.employee = employee;
    }

    public void updateUsage(final CellphoneUse use) {
        totalMinutes[use.getMonth() - 1] += use.getMinutesUsed();
        totalData[use.getMonth() - 1] += use.getDataUsed();
    }

    public String toString() {
        StringBuilder bld = new StringBuilder();
        bld.append(String.format(employee.toString()));
        bld.append(String.format("Jan,Feb,Mar,Apr,May,Jun,Jul,Aug,Sep,Oct,Nov,Dec%n"));
        bld.append(Arrays.toString(totalMinutes));
        bld.append(System.lineSeparator());
        bld.append(Arrays.toString(totalData));
        bld.append(System.lineSeparator());
        return bld.toString();
    }

}
