package com.enoca.company.entity;


import lombok.*;


import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(nullable = false)
    private String employeeName;

    @Column(nullable = false)
    private String employeeSurname;

    @Column(nullable = false)
    private double employeeSalary;

    @Column(nullable = false)
    private Date birtDate;

    @Column(nullable = false)
    private String tckn;

    @Column(nullable = false)
    private String employeeTitle;

    @Column(nullable = false)
    private String employeePhoneNumber;

    @Column(nullable = false)
    private String employeeAdress;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;


}
