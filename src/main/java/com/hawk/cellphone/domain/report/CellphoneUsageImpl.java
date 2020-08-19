package com.hawk.cellphone.domain.report;

import com.hawk.cellphone.domain.entity.CellphoneUse;
import com.hawk.cellphone.domain.entity.Employee;
import com.hawk.cellphone.domain.report.model.CellphoneUsageData;
import com.hawk.cellphone.domain.repository.CellphoneRepository;
import com.hawk.cellphone.domain.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Consumer;

@Component
class CellphoneUsageImpl implements CellphoneUsage {

    private final EmployeeRepository employeeRepository;

    private final CellphoneRepository cellphoneRepository;

    @Autowired
    CellphoneUsageImpl(final EmployeeRepository employeeRepository,final CellphoneRepository cellphoneRepository) {
        this.employeeRepository = employeeRepository;
        this.cellphoneRepository = cellphoneRepository;

    }

    @Override
    public CellphoneUsageData generateForYear(final int year) throws IOException {
        CellphoneUsageData usageData = new CellphoneUsageData(year);
        Consumer<CellphoneUse> consumeUsage = use -> {
            Employee emp = employeeRepository.findById(use.getEmployeeId());
            usageData.updateUsage(emp,use);
        };

        cellphoneRepository.findByYear(year,consumeUsage);

        return usageData;
    }
}
