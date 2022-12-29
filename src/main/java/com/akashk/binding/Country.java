package com.akashk.binding;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "country_master")
@Data
public class Country {
	
	@Id
	@GeneratedValue
	private Integer countryId;
	private String countryName;

}
