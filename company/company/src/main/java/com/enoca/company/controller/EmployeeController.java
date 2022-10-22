package com.enoca.company.controller;


import com.enoca.company.dto.EmployeeDto;
import com.enoca.company.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;


    @PostMapping
    public ResponseEntity<Long> createEmployee(@RequestBody @Valid final EmployeeDto employeeDto){
        return new ResponseEntity<>(employeeService.create(employeeDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable final Long employeeId){
        return ResponseEntity.ok(employeeService.get(employeeId));
    }

    @GetMapping("/company/{companyid}")
    public ResponseEntity<List<EmployeeDto>> findAllEmployeesByCompany(@PathVariable Long companyid){
        return ResponseEntity.ok(employeeService.getEmployeeByCompany(companyid));
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Void> updateEmployee(@PathVariable final Long employeeId,
                                               @RequestBody @Valid final EmployeeDto employeeDto){
        employeeService.update(employeeId,employeeDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable final Long employeeId){
        employeeService.delete(employeeId);
        return ResponseEntity.noContent().build();
    }

    //////
    @DeleteMapping("/companydelete/{companyId}")
    public ResponseEntity<Void> deleteEmployeesByCompany(@PathVariable final Long companyId){
        employeeService.deleteEmployeeByCompany(companyId);
        return ResponseEntity.noContent().build();
    }




}
