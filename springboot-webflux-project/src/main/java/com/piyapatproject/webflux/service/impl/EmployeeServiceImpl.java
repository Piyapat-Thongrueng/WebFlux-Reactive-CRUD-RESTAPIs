package com.piyapatproject.webflux.service.impl;

import com.piyapatproject.webflux.dto.EmployeeDto;
import com.piyapatproject.webflux.entity.Employee;
import com.piyapatproject.webflux.mapper.EmployeeMapper;
import com.piyapatproject.webflux.repository.EmployeeRepository;
import com.piyapatproject.webflux.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public Mono<EmployeeDto> saveEmployee(EmployeeDto employeeDto) {

        // convert EmployeeDto to Employee Entity before save to MondoDB
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Mono<Employee> savedEmployee =  employeeRepository.save(employee);

        return savedEmployee.map((employeeEntity) -> EmployeeMapper.mapToEmployeeDto(employeeEntity));
    }
}
