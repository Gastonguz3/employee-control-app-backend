package com.gaston.project.employee.services;

import com.gaston.project.employee.dto.EmployeeDTO;

import java.util.List;

public interface IEmployeeService {

    EmployeeDTO findById(Long id);

    List<EmployeeDTO> findAll();

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);

    void deleteById(Long id);
}
