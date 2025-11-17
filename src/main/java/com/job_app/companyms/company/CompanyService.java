package com.job_app.companyms.company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> getAllCompanies();
    boolean updateCompany(Company company, Long id);
    void addCompany(Company company);
    Company findById(Long id);
}
