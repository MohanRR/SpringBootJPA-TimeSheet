package com.personal.service;

import com.personal.entity.Company;
import com.personal.response.Response;

public interface CompanyService {

	public Response saveOrUpdateCompany(Company company);
	public Response findCompanyById(String companyId);
	public Response removeCompanyById(String companyId);
}
