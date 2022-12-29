package com.akashk.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.akashk.binding.UserCred;

public interface UserCredRepository extends JpaRepository<UserCred,Serializable>{
	
	
	@Query(" select COUNT(user) from user_cred where user=:email")
	public long checkForEmail(@Param("email") String email);
	
	 public UserCred findByUser(String user);
	 
	 public UserCred findByUserAndPassword(String user,String  password);

}
