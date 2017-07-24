package com.personal.dao;

import com.personal.entity.Company;

public interface CompanyDAO {

	public void saveOrUpdateCompany(Company company);
	public Company findCompanyById(String companyId);
	public void removeCompanyById(String companyId);
}
