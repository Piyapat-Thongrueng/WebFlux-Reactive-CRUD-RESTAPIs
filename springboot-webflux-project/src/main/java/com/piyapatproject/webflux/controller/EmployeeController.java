package com.piyapatproject.webflux.controller;

import com.piyapatproject.webflux.dto.EmployeeDto;
import com.piyapatproject.webflux.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    // Build Reactive Save Employee REST API
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.saveEmployee(employeeDto);
    }

    // Build Reactive Get Employee REST API
    @GetMapping("{id}")
    public Mono<EmployeeDto> getEmployeeById(@PathVariable("id") String employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    // Build Reactive Get All Employees REST API
    @GetMapping
    public Flux<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Build Reactive Update Employee REST API
    @PutMapping("{id}")
    public Mono<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto,
                                            @PathVariable("id") String employeeId) {
        return employeeService.updateEmployee(employeeDto, employeeId);
    }
}
