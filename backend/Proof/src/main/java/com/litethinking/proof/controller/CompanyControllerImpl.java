package com.litethinking.proof.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.litethinking.proof.model.Company;
import com.litethinking.proof.service.CompanyService;

/**
 * This class represents the Company Controller Implementation
 * @author Jorge Antonio Morales Flórez
 *
 */
@RestController
@RequestMapping("companies")
@CrossOrigin(origins = "*")
public class CompanyControllerImpl implements CompanyController {

	/**
	 * The company service interface relation
	 */
	private CompanyService companyService;
	
	/**
	 * Constructor of the class Company Controller
	 * @param companyService The company service interface
	 */
	public CompanyControllerImpl(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	@Override
	@ResponseBody
	@GetMapping("getCompanies")
	public ResponseEntity<?> getCompanies() {
		try {
			return new ResponseEntity<>(companyService.getCompanies(), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@ResponseBody
	@DeleteMapping("deleteCompany/{nit}")
	public ResponseEntity<?> deleteCompany(@PathVariable String nit) {
		try {
			return new ResponseEntity<>(companyService.deleteCompany(nit), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@ResponseBody
	@PostMapping("createCompany")
	public ResponseEntity<?> createCompany(@RequestBody Company company) {
		try {
			if(companyService.createCompany(company)) {
				return new ResponseEntity<>(true, HttpStatus.OK);
			}
			return new ResponseEntity<>("The company already exists", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@ResponseBody
	@PutMapping("updateCompany/{nit}")
	public ResponseEntity<?> updateCompany(@PathVariable String nit, @RequestBody Company company) {
		try {
			if(companyService.updateCompany(nit, company)) {
				return new ResponseEntity<>(true, HttpStatus.OK);
			}
			return new ResponseEntity<>("There's already a company with these NIT", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
