package com.personal.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.dao.CompanyDAO;
import com.personal.entity.Company;
import com.personal.response.Response;
import com.personal.service.CompanyService;
import com.personal.util.Constants;

@Service
public class CompanyServiceImpl implements CompanyService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	CompanyDAO companyDAO;
	
	@Override
	public Response saveOrUpdateCompany(Company company) {
		Response response = null;
		try{
			companyDAO.saveOrUpdateCompany(company);
			response =  new Response(company,"Company saved successfully", Constants.SUCCESS);
		}catch(Exception e){
			response = new Response(company, e.getMessage(), Constants.ERROR);
			log.error("saveOrUpdateCompany "+e.getMessage());
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public Response findCompanyById(String companyId) {
		Response response = null;
		try{
			Company company = companyDAO.findCompanyById(companyId);
			response =  new Response(company,"Company saved successfully", Constants.SUCCESS);
		}catch(Exception e){
			response = new Response(companyId, e.getMessage(), Constants.ERROR);
			log.error("saveOrUpdateCompany "+e.getMessage());
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public Response removeCompanyById(String companyId) {
		Response response = null;
		try{
			companyDAO.removeCompanyById(companyId);
			response =  new Response(companyId,"Company removed successfully", Constants.SUCCESS);
		}catch(Exception e){
			response = new Response(companyId, e.getMessage(), Constants.ERROR);
			log.error("saveOrUpdateCompany "+e.getMessage());
			e.printStackTrace();
		}
		return response;
	}

}
