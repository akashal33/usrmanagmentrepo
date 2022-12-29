package com.akashk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akashk.binding.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{

	public List<City> findByStateId(Integer stateId);

}
