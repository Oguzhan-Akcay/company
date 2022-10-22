package com.enoca.company.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import javax.persistence.Column;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {

    private Long companyId;

    @NotNull
    @Size(max = 255)
    private String companyName;

    @NotNull
    @Size(max = 255)
    private String companyAddress;

    @NotNull
    @Size(max = 255)
    private String companyPhoneNumber;

}
