package com.litethinking.proof.repository;

import org.springframework.data.repository.CrudRepository;

import com.litethinking.proof.model.Company;

/**
 * Repository interface of the Company entity
 * @author Jorge Antonio Morales Flórez
 *
 */
public interface CompanyRepository extends CrudRepository<Company, String>{

}
