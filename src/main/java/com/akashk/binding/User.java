package com.akashk.binding;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity(name = "user")
public class User {
	
	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Integer userId;
	private String firstName;
	private String lastName;
	private String email;
	private long mobileNo;
	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private Date dob;
	private String gender;
	private String country;
	private String state;
	private String city;
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private UserCred userCred;
	
	
}
