package com.akashk.restcontroller;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akashk.binding.LoginInfo;
import com.akashk.binding.UnlockAccount;
import com.akashk.binding.User;
import com.akashk.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/register")
	public String registerUser(@RequestBody User user) {
		return userService.register(user);
	}
	
	@PostMapping("/unlock")
	public String unLockAccount(@RequestBody UnlockAccount user) {
		return userService.unLockAccount(user);
	}
	
	@GetMapping("/forgotpassword/{email}")
	public String forgotPassword(@PathVariable String email){
		return userService.forgotPassword(email);
	}
	
	@GetMapping("/login")
	public String logIn(@RequestBody LoginInfo log) {
		return userService.logIn(log);
	}
	
	@GetMapping("/countries")
	public Map<Integer, String> getCountries(){
		return userService.getCountries();
	}
	
	@GetMapping("/states/{countryId}")
	public Map<Integer, String> getStates(@PathVariable int countryId){
		return userService.getStates(countryId);
	}
	
	@GetMapping("/cities/{stateId}")
	public Map<Integer, String> getCities(@PathVariable int stateId){
		return userService.getCities(stateId);
	}
	
}
