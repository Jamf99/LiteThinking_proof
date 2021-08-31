package com.litethinking.proof.service;

import com.litethinking.proof.model.Company;

/**
 * This class represents the interface of the Company Service
 * @author Jorge Antonio Morales Flórez
 *
 */
public interface CompanyService {
	
	/**
	 * Method to allow get all of the companies
	 * @return An iterable with the companies
	 */
	public Iterable<Company> getCompanies();
	
	/**
	 * Method to allow delete a company
	 * @param nit The nit of the company to delete
	 * @return True if the company has been delete, false if not
	 */
	public boolean deleteCompany(String nit);
	
	/**
	 * Method to allow create a company
	 * @param company The company to add
	 * @return True if the company has been created, false if not
	 */
	public boolean createCompany(Company company);
	
	/**
	 * Method to allow update a company
	 * @param nit The old NIT of the company
	 * @param company The new company to update
	 * @return True if the company has been updated, false if not
	 */
	public boolean updateCompany(String nit, Company company);

}
