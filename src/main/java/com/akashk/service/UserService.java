package com.akashk.service;

import java.util.Map;

import com.akashk.binding.LoginInfo;
import com.akashk.binding.UnlockAccount;
import com.akashk.binding.User;


public interface UserService {
	
	public String logIn(LoginInfo loginInfo);
	public String register(User usr);
	public String unLockAccount(UnlockAccount unlockAcc);
	public String forgotPassword(String email);
	public boolean checkValidEmail(String email);
	public Map<Integer, String> getCountries();
	public Map<Integer, String> getStates(int countryId);
	public Map<Integer, String> getCities(int stateId);
	

}
