package com.enoca.company.repository;

import com.enoca.company.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    List<Employee> getEmployeesByCompanyCompanyId(Long companyId);

    @Transactional
    @Modifying
    void deleteEmployeesByCompanyCompanyId(@Param("companyId")Long companyId);
}
