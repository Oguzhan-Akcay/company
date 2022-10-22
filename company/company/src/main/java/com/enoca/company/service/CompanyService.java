package com.enoca.company.service;

import com.enoca.company.dto.CompanyDto;
import com.enoca.company.entity.Company;
import com.enoca.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    private final EmployeeService employeeService;



    public List<CompanyDto> findAll(){
        return companyRepository.findAll(Sort.by("companyId"))
                .stream()
                .map(company -> mapToDTO(company,new CompanyDto()))
                .collect(Collectors.toList());
    }

    public CompanyDto get(final Long companyId){
        return companyRepository.findById(companyId)
                .map(company -> mapToDTO(company,new CompanyDto()))
                .orElseThrow(()-> new EntityNotFoundException("Company is not found"));
    }

    public Long create(final CompanyDto companyDto){
        final Company company = new Company();
        mapToEntity(companyDto,company);
        return companyRepository.save(company).getCompanyId();
    }

    public void update(final Long companyId, final CompanyDto companyDto){
        final Company company = companyRepository.findById(companyId)
                .orElseThrow(()-> new EntityNotFoundException("Company is not found"));
        mapToEntity(companyDto,company);
        companyRepository.save(company);
    }

    public void delete(final Long companyId){
        companyRepository.deleteById(companyId);
    }

    private CompanyDto mapToDTO(final Company company, final CompanyDto companyDto) {
        companyDto.setCompanyId(company.getCompanyId());
        companyDto.setCompanyName(company.getCompanyName());
        companyDto.setCompanyAddress(company.getCompanyAddress());
        companyDto.setCompanyPhoneNumber(company.getCompanyPhoneNumber());
        return companyDto;
    }

    private Company mapToEntity(final CompanyDto companyDto, final Company company) {
        company.setCompanyName(companyDto.getCompanyName());
        company.setCompanyAddress(companyDto.getCompanyAddress());
        company.setCompanyPhoneNumber(companyDto.getCompanyPhoneNumber());
        return company;
    }
}
