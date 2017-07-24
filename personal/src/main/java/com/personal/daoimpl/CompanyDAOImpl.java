package com.personal.daoimpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.personal.dao.CompanyDAO;
import com.personal.entity.Company;

@Transactional
@Repository
public class CompanyDAOImpl implements CompanyDAO {

	@PersistenceContext	
	private EntityManager entityManager;
	
	public void saveOrUpdateCompany(Company company){
		entityManager.persist(company);
	}

	@Override
	public Company findCompanyById(String companyId) {
		return entityManager.find(Company.class, companyId);
	}

	@Override
	public void removeCompanyById(String companyId) {
		Company company = findCompanyById(companyId);
		entityManager.remove(company);
	}
}
