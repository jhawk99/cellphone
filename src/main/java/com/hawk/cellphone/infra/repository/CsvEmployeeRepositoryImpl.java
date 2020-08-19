package com.hawk.cellphone.infra.repository;

import com.hawk.cellphone.domain.entity.Employee;
import com.hawk.cellphone.domain.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class CsvEmployeeRepositoryImpl implements EmployeeRepository {
    private static Map<Integer,Employee> employeeData;

    @Value("${employee.csv.file}")
    String employeeCsvFile;

    Function<String,Employee> toEmployee = line -> {
        String[] rec = line.split(",");
        Integer id = Integer.parseInt(rec[0]);
        LocalDate purchaseDate = LocalDate.parse(rec[2],DateTimeFormatter.BASIC_ISO_DATE);
        return new Employee(id,rec[1].trim(),purchaseDate,rec[3].trim());
    };

    @Override
    public Employee findById(final Integer id) {
        loadData();
        return employeeData.get(id);
    }

    void loadData() {
        if(employeeData == null) {
            try {
                employeeData =
                        Files.lines(Paths.get(employeeCsvFile)).skip(1).map(toEmployee).collect(Collectors.toMap(Employee::getEmployeeId,
                                                                                                                 Function.identity(),
                                                                                                                 (exist,replace) -> exist));
            } catch(Exception e) {
                throw new RuntimeException("Exception loading employees from file",e);
            }
        }

    }
}
