package com.akashk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akashk.binding.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>{

	public List<State> findByCountryId(Integer countryId);

}
