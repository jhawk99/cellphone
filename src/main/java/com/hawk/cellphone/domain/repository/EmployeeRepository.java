package com.hawk.cellphone.domain.repository;

import com.hawk.cellphone.domain.entity.Employee;

public interface EmployeeRepository {
    Employee findById(Integer id);
}
