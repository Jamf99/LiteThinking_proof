package com.litethinking.proof.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * This class represents the Company entity
 * @author Jorge Antonio Morales Flórez
 *
 */
@Data
@Entity
@Table(name = "company")
public class Company {

	/**
	 * The id of the company
	 */
	@Id
	@Column
	private String nit;
	
	/**
	 * The name of the company
	 */
	@Column
	private String name;
	
	/**
	 * The address of the company
	 */
	@Column
	private String address;
	
	/**
	 * The phone of the company
	 */
	@Column
	private String phone;
	
}
