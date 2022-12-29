package com.akashk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akashk.binding.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer>{

}
