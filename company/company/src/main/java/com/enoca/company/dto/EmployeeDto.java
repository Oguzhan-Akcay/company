package com.enoca.company.dto;

import com.enoca.company.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Long employeeId;
    private String employeeName;
    private String employeeSurname;
    private double employeeSalary;
    private Date birtDate;
    private String tckn;
    private String employeeTitle;
    private String employeePhoneNumber;
    private String employeeAdress;
    private Long company;
}
