package com.piyapatproject.webflux.service.impl;

import com.piyapatproject.webflux.dto.EmployeeDto;
import com.piyapatproject.webflux.entity.Employee;
import com.piyapatproject.webflux.mapper.EmployeeMapper;
import com.piyapatproject.webflux.repository.EmployeeRepository;
import com.piyapatproject.webflux.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
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

    @Override
    public Mono<EmployeeDto> getEmployee(String employeeId) {
        // Retrieve employee by id from Database
        Mono<Employee> savedEmployee = employeeRepository.findById(employeeId);
        return savedEmployee.map((employee) -> EmployeeMapper.mapToEmployeeDto(employee));
    }

    @Override
    public Flux<EmployeeDto> getAllEmployees() {
        // Retrieve all employees from Database
        Flux<Employee> employeeFlux = employeeRepository.findAll();
        return employeeFlux
                .map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .switchIfEmpty(Flux.empty()); // If a database is empty return this empty flux
    }

    @Override
    public Mono<EmployeeDto> updateEmployee(EmployeeDto employeeDto, String employeeId) {
        // Retrieve employee by id from Database
        Mono<Employee> employeeMono = employeeRepository.findById(employeeId);
        // Update employee data
        Mono<Employee> updatedEmployee = employeeMono.flatMap((existingEmployee) -> {
            existingEmployee.setFirstName(employeeDto.getFirstName());
            existingEmployee.setLastName(employeeDto.getLastName());
            existingEmployee.setEmail(employeeDto.getEmail());

            return employeeRepository.save(existingEmployee);
        });

        return updatedEmployee
                .map((employee) -> EmployeeMapper.mapToEmployeeDto(employee));
    }
}
