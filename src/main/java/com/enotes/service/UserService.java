package com.enotes.service;

import org.springframework.stereotype.Service;

import com.enotes.entity.User;

@Service
public interface UserService {
	
	public User saveUser(User user);
	
	public boolean existEmailCheck(String email);
	
	public String removeSessionMessage();

}
