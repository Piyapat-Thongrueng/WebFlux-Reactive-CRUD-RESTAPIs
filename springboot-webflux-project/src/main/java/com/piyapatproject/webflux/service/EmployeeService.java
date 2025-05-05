package com.piyapatproject.webflux.service;

import com.piyapatproject.webflux.dto.EmployeeDto;
import reactor.core.publisher.Mono;

public interface EmployeeService {

    Mono<EmployeeDto> saveEmployee(EmployeeDto employeeDto);
}
