package com.enoca.company.controller;

import com.enoca.company.dto.CompanyDto;
import com.enoca.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/companies")
public class CompanyController {
    private final CompanyService companyService;



    @PostMapping
    public ResponseEntity<Long> createCompany(@RequestBody @Valid final CompanyDto companyDto){
        return new ResponseEntity<>(companyService.create(companyDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies(){
        return ResponseEntity.ok(companyService.findAll());
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyDto> getCompany(@PathVariable final Long companyId){
        return ResponseEntity.ok(companyService.get(companyId));
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable final Long companyId,
                                              @RequestBody @Valid final CompanyDto companyDto){
        companyService.update(companyId, companyDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable final Long companyId){
        companyService.delete(companyId);
        return ResponseEntity.noContent().build();
    }

}
