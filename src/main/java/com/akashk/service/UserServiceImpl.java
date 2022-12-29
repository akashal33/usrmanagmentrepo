package com.akashk.service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akashk.binding.City;
import com.akashk.binding.Country;
import com.akashk.binding.LoginInfo;
import com.akashk.binding.State;
import com.akashk.binding.UnlockAccount;
import com.akashk.binding.UserCred;
import com.akashk.binding.User;
import com.akashk.repository.CityRepository;
import com.akashk.repository.CountryRepository;
import com.akashk.repository.StateRepository;
import com.akashk.repository.UserCredRepository;
import com.akashk.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserCredRepository userCredRepo;
	@Autowired
	private CountryRepository countryRepo;
	@Autowired
	private StateRepository stateRepo;
	@Autowired
	private CityRepository cityRepo;
	@Autowired
	private MailService mailService;

	@Override
	public String logIn(LoginInfo loginInfo) {
		
		UserCred user = userCredRepo.findByUserAndPassword(loginInfo.getUsername(), loginInfo.getPassword());
		
		if(user != null) {
			if(user.getStatus().equals("lock")) {
				return " sorry your acount is locked";
			}else {
				return " login successful";
			}
		}
		
		return "please check username and password";
	}

	@Override
	@Transactional(rollbackOn = MessagingException.class)
	public String register(User regUser) {
		
		long checkForEmail = userRepo.checkForEmail(regUser.getEmail());
		if(checkForEmail > 0) {
			return " email already used for registration";
		}
		User user = userRepo.save(regUser);
		
		UserCred userCred = new UserCred();
		userCred.setUserId(user.getUserId());
		userCred.setUser(regUser.getEmail());
		userCred.setPassword(passwordGenerator());
		userCred.setStatus("lock");
		regUser.setUserCred(userCred);
		userRepo.save(regUser);
		
		if(user.getUserId() != null) {	
		String body = " your password is "+userCred.getPassword()+" please change on your next login ";	
		return mailService.sendPasswordMail(userCred.getUser(), userCred.getPassword(),body);
		}else {
			return " please try again.. ";
		}
		
	}

	@Override
	public String unLockAccount(UnlockAccount unlockAcc) {
		
		long checkForEmail = userCredRepo.checkForEmail(unlockAcc.getEmail());
		
		if(checkForEmail > 0) {
			
			UserCred userCred = userCredRepo.findByUser(unlockAcc.getEmail());
				
				if( ! userCred.getPassword().equals(unlockAcc.getTempPassword())) {
					return " please check password ";
				}
				
				userCred.setPassword(unlockAcc.getNewPassword());
				userCred.setStatus("unlock");
				
				userCredRepo.save(userCred);
				
				return " Account unlocked, please proceed with login ";
				
			
		}else {
			return "please check email id ";
			
		}
		
		
	}

	@Override
	public String forgotPassword(String email) {
		
		String result = null;
		
		long checkForEmail = userCredRepo.checkForEmail(email);
		
		if(checkForEmail >0) {
			UserCred userCred = userCredRepo.findByUser(email);
			String body = " your password is "+userCred.getPassword();	
			return mailService.sendPasswordMail(userCred.getUser(), userCred.getPassword(),body);
			
			
		}else {
			
			result = " please enter valid email ";
		
		}
		
		return result;
	}

	
	public String passwordGenerator() {
		
		final String chars = "qwertyuiopasdfghjklzxcvbnm123654789QWERTYUIOPLKJHGFDSAZXCVBNM!@#$%^&*()";
		StringBuilder builder = new StringBuilder();
		SecureRandom random = new SecureRandom();
		
		for(int i=0; i<8 ;i++) {
			
					int ranIndex = random.nextInt(chars.length());
					builder.append(chars.charAt(ranIndex));
			
		}
		
		
		return builder.toString();
		
	}

	@Override
	public boolean checkValidEmail(String email) {
		
		boolean flag = true;
		
		long checkForEmail = userRepo.checkForEmail(email);
		if(checkForEmail > 0) {
			flag = false;
		}
		return flag;
	}

	@Override
	public Map<Integer, String> getCountries() {
		  List<Country> countriesList = countryRepo.findAll();
		  HashMap<Integer, String> countries = new HashMap<>();
		  for(Country country : countriesList) {
			  countries.put(country.getCountryId(), country.getCountryName());
		  }
		return countries;
	}

	@Override
	public Map<Integer, String> getStates(int countryId) {
		
		List<State> statesList = stateRepo.findByCountryId(countryId);
		HashMap<Integer, String> states = new HashMap<>();
		for(State state : statesList) {
			states.put(state.getStateId(), state.getStateName());
		}
		return states;
	}

	@Override
	public Map<Integer, String> getCities(int stateId) {
		List<City> citiesList = cityRepo.findByStateId(stateId);
		HashMap<Integer, String> cities = new HashMap<>();
		for(City city : citiesList) {
			cities.put(city.getCityId(), city.getCityName());
		}
		return cities;
	}

	
		
	
	
}
