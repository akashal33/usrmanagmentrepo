package com.akashk.binding;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "city_master")
@Data
public class City {
	@Id
	@GeneratedValue
	private Integer cityId;
	private String cityName;
	private Integer stateId;

}
