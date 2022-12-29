package com.akashk.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.akashk.binding.User;

@Repository
public interface UserRepository extends JpaRepository<User,Serializable>{
	
	@Query(" select COUNT(email) from user where email=:email")
	public long checkForEmail(@Param("email") String email);
	
	
	
}
