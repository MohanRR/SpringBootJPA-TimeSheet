package com.personal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.personal.entity.Company;
import com.personal.response.Response;
import com.personal.service.CompanyService;
import com.personal.util.Constants;

@Controller
@RequestMapping("company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	@PostMapping("/save-update")
	public ResponseEntity<Response> createNewCompany(@RequestBody Company company){
		Response response = null;
		if(company!=null){
			response = companyService.saveOrUpdateCompany(company);
		}else{
			response = new Response(company,"Company couldn't create", Constants.CUSTOM_ERROR);
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@GetMapping("{companyId}")
	public ResponseEntity<Response> findCompanyById(@PathVariable("companyId") String companyId){
		Response response = null;
		if(companyId!=null && !companyId.trim().isEmpty()){
			response = companyService.findCompanyById(companyId);
		}else{
			response = new Response(companyId,"Company couldn't found", Constants.CUSTOM_ERROR);
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("{companyId}")
	public ResponseEntity<Response> removeCompanyById(@PathVariable("companyId") String companyId){
		Response response = null;
		if(companyId!=null && !companyId.trim().isEmpty()){
			response = companyService.removeCompanyById(companyId);
		}else{
			response = new Response(companyId,"Company couldn't found", Constants.CUSTOM_ERROR);
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
}
