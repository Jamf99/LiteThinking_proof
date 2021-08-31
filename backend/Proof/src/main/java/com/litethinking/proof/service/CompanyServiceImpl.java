package com.litethinking.proof.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.litethinking.proof.model.Company;
import com.litethinking.proof.repository.CompanyRepository;

/**
 * Service class of the Company entity
 * @author Jorge Antonio Morales Flórez
 *
 */
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

	/**
	 * The company repository
	 */
	private CompanyRepository companyRepository;
	
	/**
	 * Constructor class of the CompanyService
	 * @param companyRepository Interface company repository
	 */
	public CompanyServiceImpl(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@Override
	public Iterable<Company> getCompanies() {
		return companyRepository.findAll();
	}

	@Override
	public boolean deleteCompany(String nit) {
		try {
			if(companyRepository.existsById(nit)) {
				companyRepository.deleteById(nit);
				return true;
			}
			return false;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean createCompany(Company company) {
		try { 
			if(!companyRepository.existsById(company.getNit())) {
				companyRepository.save(company);
				return true;
			}
			return false;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean updateCompany(String nit, Company company) {
		try { 
			if(companyRepository.existsById(nit)) {
				if(nit.equals(company.getNit())) {
					companyRepository.save(company);
					return true;
				}else if(!companyRepository.existsById(company.getNit())) {
					companyRepository.save(company);
					companyRepository.deleteById(nit);
					return true;
				}
			}
			return false;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
