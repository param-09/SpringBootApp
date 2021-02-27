package com.param.service;

import org.springframework.beans.factory.annotation.Autowired;		
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.param.model.User;
import com.param.repository.UserRepository;
@Service
public class MyUserDetailsService implements UserDetailsService 
{

	
	@Autowired
	private UserRepository repository;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		User user= repository.findByUsername(username);
		if (user==null)
			throw new UsernameNotFoundException("User Not Found");
		
		return new UserDetailsImpl(user);
	}

}
