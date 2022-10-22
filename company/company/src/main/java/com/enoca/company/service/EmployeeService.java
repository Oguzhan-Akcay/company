package com.enoca.company.service;

import com.enoca.company.dto.EmployeeDto;
import com.enoca.company.entity.Company;
import com.enoca.company.entity.Employee;
import com.enoca.company.repository.CompanyRepository;
import com.enoca.company.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;


    public Long create(final EmployeeDto employeeDto){
        final Employee employee = new Employee();
        mapToEntity(employeeDto, employee);
        return employeeRepository.save(employee).getEmployeeId();
    }
    public List<EmployeeDto> findAll(){
        return employeeRepository.findAll(Sort.by("employeeId"))
                .stream()
                .map(employee -> mapToDto(employee,new EmployeeDto()))
                .collect(Collectors.toList());
    }

    public EmployeeDto get(final Long employeeId){
        return employeeRepository.findById(employeeId)
                .map(employee -> mapToDto(employee, new EmployeeDto()))
                .orElseThrow(()->new EntityNotFoundException("Employee is not found"));
    }

    //Bir şirkete ait bütün çalışanları
    public List<EmployeeDto> getEmployeeByCompany(final Long companyId){
        return employeeRepository.getEmployeesByCompanyCompanyId(companyId)
                .stream()
                .map(employee -> mapToDto(employee, new EmployeeDto()))
                .collect(Collectors.toList());
    }

    public void update(Long employeeId, EmployeeDto employeeDto){
        final Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new EntityNotFoundException("Employee is not found"));
        mapToEntity(employeeDto, employee);
        employeeRepository.save(employee);
    }

    public void delete(final Long employeeId){
        employeeRepository.deleteById(employeeId);
    }

    public void deleteEmployeeByCompany(final Long companyId){
        employeeRepository.getEmployeesByCompanyCompanyId(companyId);
        employeeRepository.deleteEmployeesByCompanyCompanyId(companyId);
    }


    private EmployeeDto mapToDto(final Employee employee, final EmployeeDto employeeDto){
        employeeDto.setEmployeeId(employee.getEmployeeId());
        employeeDto.setEmployeeAdress(employee.getEmployeeAdress());
        employeeDto.setEmployeeName(employee.getEmployeeName());
        employeeDto.setEmployeeSurname(employee.getEmployeeSurname());
        employeeDto.setEmployeeSalary(employee.getEmployeeSalary());
        employeeDto.setEmployeeTitle(employee.getEmployeeTitle());
        employeeDto.setEmployeePhoneNumber(employee.getEmployeePhoneNumber());
        employeeDto.setTckn(employee.getTckn());
        employeeDto.setBirtDate(employee.getBirtDate());
        employeeDto.setCompany(employee.getCompany() == null ? null : employee.getCompany().getCompanyId());
        return employeeDto;
    }

    private Employee mapToEntity(final EmployeeDto employeeDto, final Employee employee){
        employee.setEmployeeAdress(employeeDto.getEmployeeAdress());
        employee.setEmployeeName(employeeDto.getEmployeeName());
        employee.setEmployeeSurname(employeeDto.getEmployeeSurname());
        employee.setEmployeeSalary(employeeDto.getEmployeeSalary());
        employee.setEmployeeTitle(employeeDto.getEmployeeTitle());
        employee.setEmployeePhoneNumber(employeeDto.getEmployeePhoneNumber());
        employee.setTckn(employeeDto.getTckn());
        employee.setBirtDate(employeeDto.getBirtDate());
        final Company company = employeeDto.getCompany()== null ? null : companyRepository.findById(employeeDto.getCompany())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "company not found"));
        employee.setCompany(company);
        return employee;

    }
}
