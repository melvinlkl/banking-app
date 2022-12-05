package com.uob.capstone.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.uob.capstone.dao.UserRepository;
import com.uob.capstone.entity.User;



public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	UserRepository userRepository;
	
	
	@SuppressWarnings("unused")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//to be changed to use custom query to get by username
		User user= userRepository.getUserByUserName(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		return new MyUserDetails(user);
	}
	
}
