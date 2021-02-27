package com.param.service;

import java.util.List;								
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.param.model.User;
import com.param.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;
	@Override
	public List<User> getAllUsers() 
	{
		return userRepository.findAll();
	}
	@Override
	public void saveUser(User user) 
	{
		this.userRepository.save(user);
		
	}
	@Override
	public User getUserById(long id) 
	{
		Optional<User> optional =userRepository.findById(id);
		User user=null;
		if(optional.isPresent())
		{
			user=optional.get();
		}
		else
		{
			throw new RuntimeException("User Not Found for Id :" +id);
		}
		return user;
		
	}
	@Override
	public void deleteUserById(long id) 
	{
		this.userRepository.deleteById(id);
	}
	
	@Override
	public Page<User> findPaginated(int pageNo, int pageSize) 
	{
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.userRepository.findAll(pageable);
		
	}
	@Override
	public List<User> findByKeyword(String keyword) 
	{

		return userRepository.findByKeyword(keyword);
	}
	
}	