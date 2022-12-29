package com.akashk.binding;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "user_cred")
@Data
public class UserCred {
	@Id
	@Column(name = "user_id")
	private Integer userId;
	private String user;
	private String password;
	private String status;

}
